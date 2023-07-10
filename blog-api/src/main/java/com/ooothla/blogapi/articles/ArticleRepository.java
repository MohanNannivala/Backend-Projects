package com.ooothla.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    ArticleEntity findAllBySlug(String slug);

}
