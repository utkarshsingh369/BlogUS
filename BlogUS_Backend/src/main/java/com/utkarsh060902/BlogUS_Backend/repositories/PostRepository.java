package com.utkarsh060902.BlogUS_Backend.repositories;

import com.utkarsh060902.BlogUS_Backend.entities.Category;
import com.utkarsh060902.BlogUS_Backend.entities.Post;
import com.utkarsh060902.BlogUS_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
