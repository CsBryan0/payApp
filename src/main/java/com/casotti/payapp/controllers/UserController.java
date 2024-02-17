package com.casotti.payapp.controllers;

import com.casotti.payapp.dtos.UserDTO;
import com.casotti.payapp.entities.Users;
import com.casotti.payapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDTO userDTO){
        Users newUser = userService.createUsers(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        var users = this.userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUserById(@PathVariable (value = "id") Long id) throws Exception{
        var user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
