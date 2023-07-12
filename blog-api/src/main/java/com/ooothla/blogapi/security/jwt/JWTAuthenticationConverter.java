package com.ooothla.blogapi.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class JWTAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {

        if (request.getHeader("Authorization") != null) {
            String token = request.getHeader("Authorization").split(" ")[1];
            return new JWTAuthentication(token);
        }

        return null;
    }
}
