package com.ooothla.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    ArticleEntity findAllBySlug(String slug);
    List<ArticleEntity> findAllByAuthorId(Integer id);
}
