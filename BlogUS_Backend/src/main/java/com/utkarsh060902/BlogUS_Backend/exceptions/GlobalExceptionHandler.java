package com.utkarsh060902.BlogUS_Backend.exceptions;

import com.utkarsh060902.BlogUS_Backend.payloads.DefaultApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<DefaultApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        return new ResponseEntity<>(new DefaultApiResponse(false,message,HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errorResponse=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String fieldValue=error.getDefaultMessage();
            errorResponse.put(fieldName,fieldValue);
        });
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
