package com.ooothla.blogapi.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {

    private JWTService jwtService = new JWTService();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(authentication instanceof JWTAuthentication ){
            JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
            String token = jwtAuthentication.getCredentials();
            Integer userId = jwtService.getUserIdFromJWT(token);
            jwtAuthentication.setUserId(userId);
            return jwtAuthentication;
        }
        return null;
    }
}
