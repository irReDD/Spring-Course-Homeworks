package com.stdrv.spring.homework2.services;

import com.stdrv.spring.homework2.dao.TokensDAO;
import com.stdrv.spring.homework2.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Autowired
    TokensDAO dao;

    @Override
    public Optional<AuthToken> getUserIdForToken(String token) {
        return dao.findOne(Example.of(new AuthToken(token)));
    }

    @Override
    public AuthToken validate(String userId) {
        byte[] randomBytes = new byte[128];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        return dao.insert(new AuthToken(userId, token, LocalDateTime.now()));
    }

    @Override
    public void invalidate(String tokenId) {
        dao.deleteById(tokenId);
    }
}
