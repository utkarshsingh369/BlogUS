package com.utkarsh060902.BlogUS_Backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultApiResponse {
    private boolean success;
    private String message;
    private HttpStatus httpStatus;
}
