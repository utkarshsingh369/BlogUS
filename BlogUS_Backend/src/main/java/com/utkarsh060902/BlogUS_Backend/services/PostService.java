package com.utkarsh060902.BlogUS_Backend.services;

import com.utkarsh060902.BlogUS_Backend.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId, Integer userId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts();
    void deletePost(Integer postId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);

}
