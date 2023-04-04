package com.utkarsh060902.BlogUS_Backend.payloads;

import lombok.Data;
import java .util.Date;
@Data
public class PostDto {
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;  //don't worry about mapping here ...ModelMapper is awesome
    private UserDto user;

}
