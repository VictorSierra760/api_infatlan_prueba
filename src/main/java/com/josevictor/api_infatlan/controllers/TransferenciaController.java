package com.josevictor.api_infatlan.controllers;

import com.josevictor.api_infatlan.dto.request.TransferenciaRequestDTO;
import com.josevictor.api_infatlan.dto.response.TransferenciaResponseDTO;
import com.josevictor.api_infatlan.services.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferenciaResponseDTO> realizar(@Valid @RequestBody TransferenciaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.realizar(request));
    }

}
