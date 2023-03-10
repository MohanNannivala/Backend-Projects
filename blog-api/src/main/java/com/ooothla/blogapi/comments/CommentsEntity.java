package com.ooothla.blogapi.comments;

import com.ooothla.blogapi.articles.ArticleEntity;
import com.ooothla.blogapi.commons.BaseEntity;
import com.ooothla.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentsEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    String title;

    @Column(length = 1000)
    String body;

    @ManyToOne
    ArticleEntity article;

    @ManyToOne
    UserEntity user;
}
