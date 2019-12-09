package com.stdrv.spring.homework2.controllers;

import com.stdrv.spring.homework2.model.User;
import com.stdrv.spring.homework2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/api/users/",produces = "application/json")
    public List<User> getUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(value = "/api/users/{id}",produces = "application/json")
    public User getUser(@PathVariable("id") String id) {
        return usersService.getUser(id).orElse(null);
    }
}