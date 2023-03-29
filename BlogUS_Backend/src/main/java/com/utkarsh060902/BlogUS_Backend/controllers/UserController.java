package com.utkarsh060902.BlogUS_Backend.controllers;

import com.utkarsh060902.BlogUS_Backend.exceptions.ResourceNotFoundException;
import com.utkarsh060902.BlogUS_Backend.payloads.DefaultApiResponse;
import com.utkarsh060902.BlogUS_Backend.payloads.UserDto;
import com.utkarsh060902.BlogUS_Backend.services.UserService;
import com.utkarsh060902.BlogUS_Backend.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1=null;
        try{
            userDto1=this.userService.createUser(userDto);
            return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUsers")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        try{
            return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{userId}")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int userId){
        return new ResponseEntity<>(this.userService.updateUser(userDto,userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable int userId){
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    private ResponseEntity<DefaultApiResponse> deleteUserById(@PathVariable int userId){
            this.userService.deleteUser(userId);
            return new ResponseEntity<>(new DefaultApiResponse(true,"User with ID : "+userId+" deleted successfully",HttpStatus.OK),HttpStatus.OK);
    }
}
