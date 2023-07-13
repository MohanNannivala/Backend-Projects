package com.ooothla.blogapi.security.authtokens;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class AuthTokenAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {

        if(request.getHeader("X-Auth-Token") != null){
            String token = request.getHeader("X-Auth-Token");
            return new AuthTokenAuthentication(token);
        }

        return null;
    }
}
