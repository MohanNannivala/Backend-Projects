package com.ooothla.blogapi.security.authtokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.UUID;

public class AuthTokenAuthenticationManager implements AuthenticationManager {

    private final AuthTokenService authTokenService;

    public AuthTokenAuthenticationManager(AuthTokenService authTokenService){
        this.authTokenService = authTokenService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(authentication instanceof AuthTokenAuthentication){

            AuthTokenAuthentication authTokenAuthentication = (AuthTokenAuthentication) authentication;

            String token = authTokenAuthentication.getCredentials();
            UUID uuid = UUID.fromString(token);

            Integer userId = authTokenService.getUserIdFromAuthToken(uuid);
            authTokenAuthentication.setUserId(userId);
            return authentication;

        }

        return null;
    }
}
