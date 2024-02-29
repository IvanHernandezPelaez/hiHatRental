package com.proyecto.hihatrental.controlador;

import com.proyecto.hihatrental.dto.RespuestaExepcionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExcepcionHandler {

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<RespuestaExepcionDTO> notFoundHandle(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RespuestaExepcionDTO(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<RespuestaExepcionDTO> badRequestHandle(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaExepcionDTO(exception.getMessage()));
    }
}
