package com.stodorov.spring.dao;

import com.stodorov.spring.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostDAO extends MongoRepository<BlogPost, String> {

}
