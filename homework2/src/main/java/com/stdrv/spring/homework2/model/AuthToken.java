package com.stdrv.spring.homework2.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "auth-token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthToken {
    @Id
    private String id;

    @NonNull
    private String userId;

    @NonNull
    private String token;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expirationDate;

    public AuthToken(String token) {
        this.token = token;
    }
}

