package com.ooothla.blogapi.users;

import com.ooothla.blogapi.commons.AuthType;
import com.ooothla.blogapi.users.dto.CreateUserDTO;
import com.ooothla.blogapi.users.dto.LoginUserDTO;
import com.ooothla.blogapi.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO){
        UserResponseDTO savedUser = userService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/"+savedUser.getId()))
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO, @RequestParam(required = false, value = "token") String token ){

        AuthType authType = AuthType.JWT;

        if(token!=null && token.equals("auth_token")){
            authType = AuthType.AUTH_TOKEN;
        }

        return ResponseEntity.ok(userService.loginUser(loginUserDTO, authType));
    }
}
