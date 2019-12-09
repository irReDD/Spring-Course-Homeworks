package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.dao.BlogPostDAO;
import com.stdrv.spring.homework2.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostDAO dao;

    @Override
    public List<BlogPost> getAllBlogPosts() {
        if(dao.findAll().size() == 0) {
            ArrayList<String> tags = new ArrayList<>();
            tags.add("placeholder");
            tags.add("post");
            tags.add("spring");
            tags.add("blog");

            ArrayList<BlogPost> toRet = new ArrayList<>();
            toRet.add(new BlogPost("id",
                    LocalDateTime.now(),
                    "Placeholder Blog Post",
                    "John Doe",
                    "# Currently there are no blog posts added.\n" +
                            "This blog post shows how the blog post will look like when added.\n" +
                            "It has to be able to be converted from markdown in a future release.",
                    tags,
                    "https://getuikit.com/v2/docs/images/placeholder_600x400.svg",
                    false));
            return toRet;
        }
        return dao.findAll(Sort.by(Sort.Order.desc("postDate")));
    }

    private List<BlogPost> limitText(List<BlogPost> posts) {
        List<BlogPost> ret = new ArrayList<>();
        posts.forEach(el -> {
            if(el.getContent().length() > 150) {
                ret.add(new BlogPost(el.getId(),
                        el.getPostDate(), el.getTitle(), el.getAuthor(), el.getContent(), el.getTags(), el.getImageUrl(), el.getStatus()));
            } else {
                ret.add(el);
            }
        });
        return ret;
    }

    @Override
    public List<BlogPost> getLatestBlogPosts() {
        List<BlogPost> ret = getAllBlogPosts();
        if(ret.size() > 15) {
            return getAllBlogPosts().subList(0, 14);
        } else {
            return ret;
        }
    }

    @Override
    public List<BlogPost> getByStatus(String status) {
        List<BlogPost> ret = new ArrayList<>();
        final boolean active = status.equals("active");

        getAllBlogPosts().forEach(item -> {
            if(item.getStatus() == active) {
                ret.add(item);
            }
        });
        return ret;
    }

    @Override
    public BlogPost getPost(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public BlogPost add(BlogPost blogPost) {
        return dao.insert(blogPost);
    }

    @Override
    public void remove(String id) {
        dao.deleteById(id);
    }
}
