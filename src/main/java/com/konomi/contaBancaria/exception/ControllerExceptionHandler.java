package com.konomi.contaBancaria.exception;

import com.konomi.contaBancaria.payload.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threadDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDto exceptionDto = new ExceptionDto("Usuario ja cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity thread404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threadGeneralException(Exception exception)  {
        ExceptionDto exceptionDto = new ExceptionDto(exception.getMessage(), "500");
    return ResponseEntity.internalServerError().body(exceptionDto);
    }
}
