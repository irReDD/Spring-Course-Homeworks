package com.stdrv.spring.homework2.dao;

import com.stdrv.spring.homework2.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostDAO extends MongoRepository<BlogPost, String> {

}
