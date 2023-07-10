package com.ooothla.blogapi.users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    Integer id;
    String userName;
    String email;
    String bio;
    String image;
    String token;
}
