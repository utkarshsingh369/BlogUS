package com.utkarsh060902.BlogUS_Backend.exceptions;

import com.utkarsh060902.BlogUS_Backend.payloads.DefaultApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<DefaultApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        return new ResponseEntity<>(new DefaultApiResponse(false,message,HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
