package com.senai.miniprojetoeducationm1s12.exceptions;

import com.senai.miniprojetoeducationm1s12.exceptions.error.NotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e) {
        log.error("Falha inesperada: {}, {}", e.getMessage(), e.getCause().getStackTrace());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<?> handler(NotFountException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
