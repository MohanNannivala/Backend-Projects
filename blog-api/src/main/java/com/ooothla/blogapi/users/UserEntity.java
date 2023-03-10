package com.ooothla.blogapi.users;


import com.ooothla.blogapi.articles.ArticleEntity;
import com.ooothla.blogapi.commons.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Data
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String userName;
    String email;
    String password; // TODO: Hash this
    String bio;
    String image;

    @ManyToMany(mappedBy = "likedBy")
    List<ArticleEntity> likedArticles;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    List<UserEntity> following;

    @ManyToMany(mappedBy = "following")
    List<UserEntity> followers;
}
