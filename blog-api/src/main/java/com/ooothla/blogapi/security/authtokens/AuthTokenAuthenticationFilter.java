package com.ooothla.blogapi.security.authtokens;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class AuthTokenAuthenticationFilter extends AuthenticationFilter {

    public AuthTokenAuthenticationFilter(AuthTokenService authTokenService) {

        super(new AuthTokenAuthenticationManager(authTokenService), new AuthTokenAuthenticationConverter());

        setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });

    }
}
