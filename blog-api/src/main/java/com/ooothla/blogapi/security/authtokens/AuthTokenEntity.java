package com.ooothla.blogapi.security.authtokens;

import com.ooothla.blogapi.users.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "auth_token")
@Data
public class AuthTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    UserEntity user;
}
