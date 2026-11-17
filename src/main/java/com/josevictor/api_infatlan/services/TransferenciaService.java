package com.josevictor.api_infatlan.services;

import com.josevictor.api_infatlan.dto.request.TransferenciaRequestDTO;
import com.josevictor.api_infatlan.dto.response.TransferenciaResponseDTO;

public interface TransferenciaService {

    TransferenciaResponseDTO realizar(TransferenciaRequestDTO solicitud);
}
