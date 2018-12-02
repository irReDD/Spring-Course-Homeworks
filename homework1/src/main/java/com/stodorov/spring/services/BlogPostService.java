package com.stodorov.spring.services;

import com.stodorov.spring.model.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getAllBlogPosts();
    List<BlogPost> getLatestBlogPosts();
    List<BlogPost> getByStatus(String status);
    BlogPost getPost(String id);
    BlogPost add(BlogPost blogPost);
    void remove(String id);
}
