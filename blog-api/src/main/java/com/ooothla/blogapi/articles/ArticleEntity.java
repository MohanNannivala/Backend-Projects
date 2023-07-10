package com.ooothla.blogapi.articles;

import com.ooothla.blogapi.commons.BaseEntity;
import com.ooothla.blogapi.users.UserEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity(name = "articles")
@Data
public class ArticleEntity extends BaseEntity {

    @Column(unique = true, nullable = true, length = 150)
    String slug;
    @Column(nullable = true, length = 200)
    String title;
    @Column(nullable = true, length = 8000)
    String subtitle;
    String body;
    //String[] tags;

    @ManyToOne
    UserEntity author;

    @ManyToMany
    List<UserEntity> likedBy;
}
