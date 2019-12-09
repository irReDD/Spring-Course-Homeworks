package com.stodorov.spring.controllers;

import com.stodorov.spring.model.BlogPost;
import com.stodorov.spring.model.BlogPostBase;
import com.stodorov.spring.services.BlogPostService;
import com.stodorov.spring.services.ImageStoreService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class BlogPostController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlogPostController.class);

    @Autowired
    BlogPostService service;

    @Autowired
    ImageStoreService imageStore;

    @GetMapping(value = "/latest-posts")
    public ModelAndView getPosts(String status) {
        ModelAndView modelAndView = new ModelAndView("latest-posts");
        if(status == null || status.equals("all")) {
            modelAndView.addObject("allPosts", service.getLatestBlogPosts());
            modelAndView.addObject("status", "all");
        } else {
            modelAndView.addObject("allPosts", service.getByStatus(status));
            modelAndView.addObject("status", status);
        }
        return modelAndView;
    }

    @GetMapping(value = "/all-posts")
    public ModelAndView getAllPosts() {
        ModelAndView modelAndView = new ModelAndView("all-posts");
        modelAndView.addObject("allPosts", service.getAllBlogPosts());
        return modelAndView;
    }

    @GetMapping(value = "/new-post")
    public String newPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        return "new-post";
    }

    @PostMapping(value = "/new-post")
    public String addPost(@ModelAttribute("blogPost") BlogPostBase blogPostBase) {
        log.info("New Post Submitted", blogPostBase);
        List<String> tags = new ArrayList<>(Arrays.asList(blogPostBase.getTags().split(", ")));

        boolean active = true;
        if(blogPostBase.getStatus() != null && blogPostBase.getStatus()) {
            active = false;
        }

        String imageUrl = blogPostBase.getImageUrl();
        if(imageUrl == null) {
            imageUrl = "";
        }

        BlogPost blogPost = new BlogPost(LocalDateTime.now(), blogPostBase.getTitle(), blogPostBase.getAuthor(), blogPostBase.getContent(), tags, imageUrl, active);
        service.add(blogPost);
        return "redirect:/all-posts";
    }

    @GetMapping(value = "/edit-post")
    public ModelAndView editPost(String id) {
        log.info("Editing post", id);
        ModelAndView modelAndView = new ModelAndView("edit-post");
        BlogPost bp = service.getPost(id);
        String tags = "";
        for(String tag : bp.getTags()) {
            if (!tags.isEmpty()) {
                tags = tags.concat(", ");
            }
            tags = tags.concat(tag);
        }
        BlogPostBase toSend = new BlogPostBase(bp.getId(), bp.getTitle(), bp.getAuthor(), bp.getContent(), tags, bp.getImageUrl(), !bp.getStatus());
        modelAndView.addObject("blogPost", toSend);
        return modelAndView;
    }

    @PostMapping(value = "/edit-post")
    public String completeEditPost(@ModelAttribute("blogPost") BlogPostBase blogPostBase) {
        log.info("Edited post submitted", blogPostBase);
        BlogPost oldPost = service.getPost(blogPostBase.getId());
        if(oldPost == null) {
            log.error("Trying to edit a post but could not find it", blogPostBase);
            return "redirect:/all-posts";
        }
        List<String> tags = new ArrayList<>(Arrays.asList(blogPostBase.getTags().split(", ")));

        boolean active = true;
        if(blogPostBase.getStatus() != null && blogPostBase.getStatus()) {
            active = false;
        }

        String imageUrl = blogPostBase.getImageUrl();
        if(imageUrl == null) {
            imageUrl = "";
        }

        BlogPost blogPost = new BlogPost(oldPost.getId(), oldPost.getPostDate(), blogPostBase.getTitle(), blogPostBase.getAuthor(), blogPostBase.getContent(), tags, imageUrl, active);
        service.remove(oldPost.getId());
        service.add(blogPost);
        return "redirect:/all-posts";
    }


    @PostMapping(value = "/delete-post")
    public String removePost(String id) {
        log.info("Deleting post", id);
        service.remove(id);
        return "redirect:/all-posts";
    }
}

