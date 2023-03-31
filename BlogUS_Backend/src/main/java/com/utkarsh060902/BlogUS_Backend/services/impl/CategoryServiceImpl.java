package com.utkarsh060902.BlogUS_Backend.services.impl;

import com.utkarsh060902.BlogUS_Backend.entities.Category;
import com.utkarsh060902.BlogUS_Backend.exceptions.ResourceNotFoundException;
import com.utkarsh060902.BlogUS_Backend.payloads.CategoryDto;
import com.utkarsh060902.BlogUS_Backend.repositories.CategoryRepository;
import com.utkarsh060902.BlogUS_Backend.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryToDto(this.categoryRepository.save(dtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDesc(categoryDto.getCategoryDesc());
        return categoryToDto(this.categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        return categoryToDto(this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId)));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(this::categoryToDto).collect(Collectors.toList());
    }



    private Category dtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }

    private CategoryDto categoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
