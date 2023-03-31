package com.utkarsh060902.BlogUS_Backend.services;

import com.utkarsh060902.BlogUS_Backend.payloads.CategoryDto;

import java.util.List;


public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
    CategoryDto getCategoryById(int categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(int categoryId);
}
