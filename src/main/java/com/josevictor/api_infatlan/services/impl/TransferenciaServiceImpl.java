package com.josevictor.api_infatlan.services.impl;

import com.josevictor.api_infatlan.dto.request.TransferenciaRequestDTO;
import com.josevictor.api_infatlan.dto.response.TransferenciaResponseDTO;
import com.josevictor.api_infatlan.entities.accounts;
import com.josevictor.api_infatlan.entities.transfers;
import com.josevictor.api_infatlan.exceptions.Excepciones;
import com.josevictor.api_infatlan.exceptions.NoEncontrado;
import com.josevictor.api_infatlan.repositories.CuentaRepository;
import com.josevictor.api_infatlan.repositories.TransferenciaRepository;
import com.josevictor.api_infatlan.services.TransferenciaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final CuentaRepository cuentaRepository;
    private final TransferenciaRepository transferenciaRepository;
    public TransferenciaServiceImpl(CuentaRepository cuentaRepository,
            TransferenciaRepository transferenciaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    @Transactional
    public TransferenciaResponseDTO realizar(TransferenciaRequestDTO solicitud) {
        Long idCuentaOrigen = solicitud.getSourceAccountId();
        Long idCuentaDestino = solicitud.getDestinationAccountId();
        BigDecimal monto = solicitud.getAmount();

        if (idCuentaOrigen.equals(idCuentaDestino)) {
            throw new Excepciones("La cuenta origen y destino deben ser diferentes");
        }

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Excepciones("El monto debe ser mayor a cero");
        }

        Long primerId = idCuentaOrigen < idCuentaDestino ? idCuentaOrigen : idCuentaDestino;
        Long segundoId = idCuentaOrigen < idCuentaDestino ? idCuentaDestino : idCuentaOrigen;

        accounts primeraCuenta = cuentaRepository.findWithLockById(primerId)
                .orElseThrow(() -> new NoEncontrado("Cuenta con id " + primerId + " no encontrada"));
        accounts segundaCuenta = cuentaRepository.findWithLockById(segundoId)
                .orElseThrow(() -> new NoEncontrado("Cuenta con id " + segundoId + " no encontrada"));

        accounts cuentaOrigen = idCuentaOrigen.equals(primerId) ? primeraCuenta : segundaCuenta;
        accounts cuentaDestino = idCuentaDestino.equals(primerId) ? primeraCuenta : segundaCuenta;

        if (cuentaOrigen.getBalance().compareTo(monto) < 0) {
            throw new Excepciones("Saldo insuficiente");
        }

        cuentaOrigen.setBalance(cuentaOrigen.getBalance().subtract(monto));
        cuentaDestino.setBalance(cuentaDestino.getBalance().add(monto));

        transfers transferencia = new transfers();
        transferencia.setSourceAccount(cuentaOrigen);
        transferencia.setDestinationAccount(cuentaDestino);
        transferencia.setAmount(monto);

        transfers transferenciaGuardada = transferenciaRepository.saveAndFlush(transferencia);

        TransferenciaResponseDTO respuesta = new TransferenciaResponseDTO();
        respuesta.setId(transferenciaGuardada.getId());
        respuesta.setSourceAccountId(cuentaOrigen.getId());
        respuesta.setDestinationAccountId(cuentaDestino.getId());
        respuesta.setAmount(transferenciaGuardada.getAmount());
        respuesta.setCreatedAt(transferenciaGuardada.getCreatedAt());
        return respuesta;
    }
}
