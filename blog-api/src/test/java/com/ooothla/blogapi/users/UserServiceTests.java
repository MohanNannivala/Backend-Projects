package com.ooothla.blogapi.users;


import com.ooothla.blogapi.users.dto.CreateUserDTO;
import com.ooothla.blogapi.users.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    public void testCreateUser(){
        CreateUserDTO newUserDto = new CreateUserDTO();
        newUserDto.setUserName("mohan");
        newUserDto.setEmail("mohan.nannivala.sajjan@gmail.com");
        newUserDto.setPassword("mohan@123");
        UserResponseDTO userResponseDTO = userService.createUser(newUserDto);
        assertNotNull(userResponseDTO);
    }
}
