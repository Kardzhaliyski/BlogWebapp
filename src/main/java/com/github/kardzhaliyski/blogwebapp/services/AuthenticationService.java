package com.github.kardzhaliyski.blogwebapp.services;

import com.github.kardzhaliyski.blogwebapp.mappers.AuthTokenMapper;
import com.github.kardzhaliyski.blogwebapp.models.AuthToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AuthenticationService {

    public static AuthenticationService instance = null;
    private final AuthTokenMapper tokenMapper;
    private final Random random;
    private Map<String, AuthToken> tokenMap = new HashMap<>();


    private AuthenticationService(AuthTokenMapper tokenMapper) {
        this.tokenMap = new HashMap<>();
        this.random = new Random();
        this.tokenMapper = tokenMapper;
    }

    public boolean isValid(String token) {
        if (tokenMap.containsKey(token)) {
            return true;
        }

        AuthToken authToken = tokenMapper.getBy(token);
        if (authToken == null) {
            return false;
        }

        tokenMap.put(token, authToken);
        return true;
    }

    public String createNewToken(String username) {
        int salt = random.nextInt();
        String token = DigestUtils.sha1Hex(username + "$" + salt);
        AuthToken authToken = new AuthToken(username, token);
        tokenMapper.addToken(authToken);
        tokenMap.put(token, authToken);
        return token;
    }

    public AuthToken getToken(String token) {
        AuthToken authToken = tokenMap.get(token);
        if(authToken == null) {
            throw new IllegalStateException("Token hasn't been cached. Try login again.");
        }

        return authToken;
    }

    public AuthToken getAuthToken(String authHeader) {
        if(authHeader == null) {
            return null;
        }

        boolean correctSchema = authHeader.startsWith("Bearer ");
        if (!correctSchema) {
           return null;
        }

        String token = authHeader.substring(7);
        if (!isValid(token)) {
            return null;
        }

        return getToken(token);
    }
}
