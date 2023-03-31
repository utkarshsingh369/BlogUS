package com.utkarsh060902.BlogUS_Backend.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 3,message = "Name must be of at least 3 letters")
    private String name;

    @Email(message = "Not a valid email address")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*]).{8,}$", message = "Password => must contain at least one capital letter,    must contain at least one small letter,    must contain at least one number,    must contain at least one special character,    must have minimum size 8 ")
    private String password;

    private String about;
}
