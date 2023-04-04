package com.utkarsh060902.BlogUS_Backend.controllers;

import com.utkarsh060902.BlogUS_Backend.payloads.PostDto;
import com.utkarsh060902.BlogUS_Backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/{categoryId}/{userId}")
    private ResponseEntity<PostDto> createPostHandler(@RequestBody PostDto postDto, @PathVariable int categoryId, @PathVariable int userId) {
        try {
            return new ResponseEntity<>(this.postService.createPost(postDto, categoryId, userId), HttpStatus.CREATED);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
