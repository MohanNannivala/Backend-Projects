package com.ooothla.blogapi.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;


public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter() {

        super(new JWTAuthenticationManager(), new JWTAuthenticationConverter());

         setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);

        });
    }

}
