package com.stodorov.spring.model;

import com.stodorov.spring.controllers.BlogPostController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document(collection = "blogPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BlogPost {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlogPost.class);

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

    /**
     * @return The parsed markdown content of the blog post
     */
    public String getParsedContent() {
        if (this.content != null) {
            try {
                Parser parser = Parser.builder().build();
                Node document = parser.parse(this.content);
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                return renderer.render(document);
            } catch (Exception e) {
                log.warn("Something went wrong parsing Markdown", e);
            }
        }
        return this.content;
    }

    /**
     * @return Human readable post date
     */
    public String getHRPostDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        return this.postDate.format(formatter);
    }
}
