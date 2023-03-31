package com.utkarsh060902.BlogUS_Backend.services.impl;

import com.utkarsh060902.BlogUS_Backend.entities.User;
import com.utkarsh060902.BlogUS_Backend.exceptions.ResourceNotFoundException;
import com.utkarsh060902.BlogUS_Backend.payloads.UserDto;
import com.utkarsh060902.BlogUS_Backend.repositories.UserRepository;
import com.utkarsh060902.BlogUS_Backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.userRepository.save(this.dtoToUser(userDto));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);
    }

    private UserDto userToDto(User user){
        return this.modelMapper.map(user,UserDto.class);
//        UserDto dto=new UserDto();
//        dto.setId(user.getId());
//        dto.setName(user.getName());
//        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
//        dto.setAbout(user.getAbout());
//        return dto;
    }
    private User dtoToUser(UserDto userDto){
        return this.modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
    }

}
