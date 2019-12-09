package com.stdrv.spring.homework2.dao;

import com.stdrv.spring.homework2.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersDAO extends MongoRepository<User, String> {
}
