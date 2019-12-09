package com.stdrv.spring.homework2.model;

import lombok.*;
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
    private Object imageUrl;

    @NonNull
    private Boolean status;

}
