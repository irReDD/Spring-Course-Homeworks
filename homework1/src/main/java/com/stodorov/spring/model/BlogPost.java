package com.stodorov.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blogPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BlogPost {

    @Id
    private String id;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postDate;

    @NonNull
    private String title;

    @NonNull
    private String author;

    @NonNull
    private String content;

    @NonNull
    private List<String> tags;

    @NonNull
    private String imageUrl;

    @NonNull
    private Boolean status;
}
