package com.josevictor.api_infatlan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Error de validación");
        respuesta.put("errores", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(NoEncontrado.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(NoEncontrado ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(Duplicados.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(Duplicados ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("status", HttpStatus.CONFLICT.value());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }

    @ExceptionHandler(Excepciones.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(Excepciones ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Los datos enviados no tienen un formato válido");
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}
