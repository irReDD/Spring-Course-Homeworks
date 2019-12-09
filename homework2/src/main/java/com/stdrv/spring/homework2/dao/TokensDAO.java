package com.stdrv.spring.homework2.dao;

import com.stdrv.spring.homework2.model.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokensDAO extends MongoRepository<AuthToken, String> {
}
