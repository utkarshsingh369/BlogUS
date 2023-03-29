package com.utkarsh060902.BlogUS_Backend.payloads;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
