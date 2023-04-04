package com.utkarsh060902.BlogUS_Backend.controllers;

import com.utkarsh060902.BlogUS_Backend.payloads.CategoryDto;
import com.utkarsh060902.BlogUS_Backend.payloads.DefaultApiResponse;
import com.utkarsh060902.BlogUS_Backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDto> createCategoryHandler(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    private ResponseEntity<CategoryDto> updateCategoryHandler(@RequestBody CategoryDto categoryDto, @PathVariable int categoryId){
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto,categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    private ResponseEntity<DefaultApiResponse> deleteCategoryHandler(@PathVariable int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new DefaultApiResponse(true,"Category with ID : "+categoryId+" deleted successfully",HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    private ResponseEntity<List<CategoryDto>> getAllCategoriesHandler(){
        return new ResponseEntity<>(this.categoryService.getAllCategories(),HttpStatus.FOUND);
    }

    @GetMapping("/getCategoryById/{categoryId}")
    private ResponseEntity<CategoryDto> getCategoryByIdHandler(@PathVariable int categoryId){
        return new ResponseEntity<>(this.categoryService.getCategoryById(categoryId),HttpStatus.FOUND);
    }


}
