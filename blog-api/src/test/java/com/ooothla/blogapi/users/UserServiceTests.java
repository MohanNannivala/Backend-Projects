package com.ooothla.blogapi.users;


import com.ooothla.blogapi.security.authtokens.AuthTokenRepository;
import com.ooothla.blogapi.security.authtokens.AuthTokenService;
import com.ooothla.blogapi.security.jwt.JWTService;
import com.ooothla.blogapi.users.dto.CreateUserDTO;
import com.ooothla.blogapi.users.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    private AuthTokenService authTokenService;


    private UserService getUserService(){
        if(userService == null){
            userService = new UserService(userRepository, new ModelMapper(), new BCryptPasswordEncoder(), new JWTService(), authTokenService);
        }
        return userService;
    }


    @Test
    public void testCreateUser(){
        CreateUserDTO newUserDto = new CreateUserDTO();
        newUserDto.setUserName("mohan");
        newUserDto.setEmail("mohan.nannivala.sajjan@gmail.com");
        newUserDto.setPassword("mohan@123");
        UserResponseDTO userResponseDTO = getUserService().createUser(newUserDto);
        assertNotNull(userResponseDTO);
    }
}
