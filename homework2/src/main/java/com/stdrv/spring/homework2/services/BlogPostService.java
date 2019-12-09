package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.model.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getAllBlogPosts();
    List<BlogPost> getLatestBlogPosts();
    List<BlogPost> getByStatus(String status);
    BlogPost getPost(String id);
    BlogPost add(BlogPost blogPost);
    void remove(String id);
}
