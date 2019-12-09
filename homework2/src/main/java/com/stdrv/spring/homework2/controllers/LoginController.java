package com.stdrv.spring.homework2.controllers;

import com.stdrv.spring.homework2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        return usersService.login(username, password); //orElse throw 403
    }
}
