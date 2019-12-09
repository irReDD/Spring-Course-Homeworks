package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.model.AuthToken;
import com.stdrv.spring.homework2.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    String login(String username, String password);
    List<User> getAllUsers();
    Optional<User> getUser(String id);
    Optional<User> getUserByToken(AuthToken id);
    User register(User blogPost);
    void remove(String id);
}
