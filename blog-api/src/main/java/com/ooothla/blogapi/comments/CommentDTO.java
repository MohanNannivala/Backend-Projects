package com.ooothla.blogapi.comments;

import com.ooothla.blogapi.articles.ArticleEntity;
import com.ooothla.blogapi.users.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class CommentDTO {
    String title;
    String body;
}