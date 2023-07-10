package com.ooothla.blogapi.comments;

import com.ooothla.blogapi.articles.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentsEntity, Integer> {

    @Transactional
    public void deleteByArticleAndId(ArticleEntity articleEntity, int id);
    public List<CommentsEntity> findAllByArticle(ArticleEntity articleEntity);
}
