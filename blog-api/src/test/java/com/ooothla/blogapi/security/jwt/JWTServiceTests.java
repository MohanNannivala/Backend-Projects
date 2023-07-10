package com.ooothla.blogapi.security.jwt;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JWTServiceTests {

    private JWTService service = new JWTService();

    @Test
    void canCreateJWTFromUserId(){
        Integer userId =  1122;
        String token = service.createJWT(userId.toString(), new Date(1688958120), new Date(1689562920));
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMTIyIiwiaWF0IjoxNjg4OTU4LCJleHAiOjE2ODk1NjJ9.idkzEw4ZrLTjs2_NYsenHUy7szY_bCGXdhzSVeDAjJI", token);
    }

    @Test
    void canVerifyJWT(){
        String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMTIyIiwiaWF0IjoxNjg4OTU4MTIwLCJleHAiOjE2ODk1NjI5MjB9.MMelVbdvmziAhFT5bd3Egp2m4tryx1B2xsEASRPEOUE";
        Integer userId = service.getUserIdFromJWT(token);
        assertEquals("1122", userId.toString());
    }

}
