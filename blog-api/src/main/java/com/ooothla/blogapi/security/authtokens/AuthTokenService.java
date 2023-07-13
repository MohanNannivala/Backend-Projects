package com.ooothla.blogapi.security.authtokens;

import com.ooothla.blogapi.users.UserEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository){
        this.authTokenRepository = authTokenRepository;
    }

    public UUID createAuthToken(UserEntity userEntity){
        AuthTokenEntity authTokenEntity = new AuthTokenEntity();
        authTokenEntity.setUser(userEntity);
        AuthTokenEntity savedAuthToken = authTokenRepository.save(authTokenEntity);
        return savedAuthToken.getId();
    }

    public Integer getUserIdFromAuthToken(UUID token){
        AuthTokenEntity authTokenEntity = authTokenRepository.findById(token).orElseThrow(() -> new BadCredentialsException("Invalid Auth Token"));
        return authTokenEntity.getUser().getId();
    }
}
