package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.dao.UsersDAO;
import com.stdrv.spring.homework2.model.AuthToken;
import com.stdrv.spring.homework2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private static final User INITIAL_USER = new User("Administrator", "admin@blog.local", "", "admin", "admin", LocalDateTime.now());

    private final UsersDAO dao;
    private final TokenService tokenService;

    @Autowired
    public UsersServiceImpl(UsersDAO dao, TokenService tokenService) {
        this.dao = dao;
        this.tokenService = tokenService;
        if (dao.findAll().isEmpty()) {
            dao.insert(INITIAL_USER);
        }
    }

    @Override
    public String login(String username, String password) {
        User usr = dao.findOne(Example.of(new User(username, password))).orElse(null);
        if (usr == null) {
            throw new UsernameNotFoundException(username);
        }
        AuthToken token = tokenService.validate(usr.getId());
        return token.getToken();
    }

    @Override
    public List<User> getAllUsers() {
        return dao.findAll(Sort.by(Sort.Order.desc("regDate")));
    }

    @Override
    public Optional<User> getUser(String id) {
        return dao.findById(id);
    }

    @Override
    public Optional<User> getUserByToken(AuthToken id) {
        return dao.findById(id.getUserId());
    }

    @Override
    public User register(User blogPost) {
        return dao.insert(blogPost);
    }

    @Override
    public void remove(String id) {
        dao.deleteById(id);
    }
}
