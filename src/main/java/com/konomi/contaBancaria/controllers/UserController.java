package com.konomi.contaBancaria.controllers;

import com.konomi.contaBancaria.domain.user.User;
import com.konomi.contaBancaria.payload.UserDto;
import com.konomi.contaBancaria.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User newUser = usersService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = usersService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
