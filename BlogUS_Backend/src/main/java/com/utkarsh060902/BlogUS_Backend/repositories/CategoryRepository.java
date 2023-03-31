package com.utkarsh060902.BlogUS_Backend.repositories;

import com.utkarsh060902.BlogUS_Backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
