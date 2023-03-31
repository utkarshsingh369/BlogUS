package com.utkarsh060902.BlogUS_Backend.payloads;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    private int categoryId;

    @NotEmpty()
    @Size(max = 20,min = 2)
    private String categoryName;

    @NotEmpty
    @Size(max = 200)
    private String categoryDesc;
}
