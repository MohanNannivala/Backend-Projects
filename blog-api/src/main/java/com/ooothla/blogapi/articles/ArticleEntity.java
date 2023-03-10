package com.ooothla.blogapi.articles;

import com.ooothla.blogapi.commons.BaseEntity;
import com.ooothla.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 150)
    String slug;
    @Column(nullable = false, length = 200)
    String title;
    @Column(nullable = false, length = 8000)
    String subtitle;
    String body;
    //String[] tags;

    @ManyToOne
    UserEntity author;

    @ManyToMany
    List<UserEntity> likedBy;
}
