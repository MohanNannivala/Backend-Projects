package com.ooothla.blogapi.articles;

import com.ooothla.blogapi.users.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDTO {
    String title;
    String subtitle;
    String body;
    String slug;
}
