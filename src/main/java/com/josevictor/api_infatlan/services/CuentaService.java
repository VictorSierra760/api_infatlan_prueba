package com.josevictor.api_infatlan.services;

import com.josevictor.api_infatlan.dto.response.CuentaResponseDTO;

import java.util.List;

public interface CuentaService {

    CuentaResponseDTO buscarPorId(Long id);

    List<CuentaResponseDTO> listar();
}
