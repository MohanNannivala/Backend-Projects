package com.ooothla.blogapi.users.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDTO {
    String email;
    String userName;
    String password;
}
