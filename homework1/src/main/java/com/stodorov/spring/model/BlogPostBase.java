package com.stodorov.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BlogPostBase {
    @NonNull
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String author;

    @NonNull
    private String content;

    private String tags;

    private String imageUrl;

    private Boolean status;
}
