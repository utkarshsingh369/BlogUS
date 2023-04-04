package com.utkarsh060902.BlogUS_Backend.services.impl;

import com.utkarsh060902.BlogUS_Backend.entities.Post;
import com.utkarsh060902.BlogUS_Backend.exceptions.ResourceNotFoundException;
import com.utkarsh060902.BlogUS_Backend.payloads.PostDto;
import com.utkarsh060902.BlogUS_Backend.repositories.CategoryRepository;
import com.utkarsh060902.BlogUS_Backend.repositories.PostRepository;
import com.utkarsh060902.BlogUS_Backend.repositories.UserRepository;
import com.utkarsh060902.BlogUS_Backend.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Integer categoryId, Integer userId) {
        Post newPost=dtoToPost(postDto);
        newPost.setImageName("default.png");
        newPost.setAddedDate(new Date());
        newPost.setUser(this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId)));
        newPost.setCategory(this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId)));
        return postToDto(this.postRepository.save(newPost));

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post updatingPost=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId));
        updatingPost.setTitle(postDto.getTitle());
        updatingPost.setContent(postDto.getContent());
        return postToDto(this.postRepository.save(updatingPost));
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return postToDto(this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId)));
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts=this.postRepository.findAll();
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public void deletePost(Integer postId) {
        this.postRepository.delete(this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId)));
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        return this.postRepository.findByUser(this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId)))
                .stream()
                .map(this::postToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        return null;
    }

    private PostDto postToDto(Post post){
        return this.modelMapper.map(post,PostDto.class);
    }
    private Post dtoToPost(PostDto postDto){
        return this.modelMapper.map(postDto,Post.class);
    }
}
