package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.model.AuthToken;

import java.util.Optional;

public interface TokenService {
    Optional<AuthToken> getUserIdForToken(String id);
    AuthToken validate(String userId);
    void invalidate(String tokenId);
}
