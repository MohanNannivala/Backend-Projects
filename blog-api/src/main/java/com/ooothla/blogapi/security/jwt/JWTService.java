package com.ooothla.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private Algorithm algorithm = Algorithm.HMAC256("SECRET SIGING KEY (should be in env or config)");

    public String createJWT(Integer userId){
        return createJWT(userId.toString(),
                new Date(),
                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)
        );
    }

    protected String createJWT(String userId, Date iat, Date exp){

        String token = JWT.create()
                .withSubject(userId)
                .withIssuedAt(iat)
                .withExpiresAt(exp)
                .sign(algorithm);

        return token;
    }

    public Integer getUserIdFromJWT(String jwt){

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String subject = decodedJWT.getSubject();
        return Integer.parseInt(subject);

    }
}
