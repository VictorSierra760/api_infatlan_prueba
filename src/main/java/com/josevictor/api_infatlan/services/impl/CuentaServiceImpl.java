package com.josevictor.api_infatlan.services.impl;

import com.josevictor.api_infatlan.dto.response.CuentaResponseDTO;
import com.josevictor.api_infatlan.entities.accounts;
import com.josevictor.api_infatlan.exceptions.NoEncontrado;
import com.josevictor.api_infatlan.repositories.CuentaRepository;
import com.josevictor.api_infatlan.services.CuentaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public CuentaResponseDTO buscarPorId(Long idCuenta) {
        accounts cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new NoEncontrado("Cuenta con id " + idCuenta + " no encontrada"));

        return convertirResponse(cuenta);
    }

    @Override
    public List<CuentaResponseDTO> listar() {
        return cuentaRepository.findAll()
                .stream()
                .map(this::convertirResponse)
                .toList();
    }

    private CuentaResponseDTO convertirResponse(accounts cuenta) {
        CuentaResponseDTO respuesta = new CuentaResponseDTO();
        respuesta.setId(cuenta.getId());
        respuesta.setHolder_name(cuenta.getHolder_name());
        respuesta.setBalance(cuenta.getBalance());

        return respuesta;
    }

}
