package com.ooothla.blogapi.comments;

import com.ooothla.blogapi.articles.ArticleEntity;
import com.ooothla.blogapi.articles.ArticleRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CommentService {

    public final CommentRepository commentRepository;
    public final ArticleRepository articleRepository;
    public final ModelMapper modelMapper;

    CommentService(CommentRepository commentRepository, ModelMapper modelMapper, ArticleRepository articleRepository){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }

    public CommentsEntity createComment(String slug, CommentDTO commentDTO){
        ArticleEntity articleEntity = articleRepository.findAllBySlug(slug);
        CommentsEntity commentsEntity = modelMapper.map(commentDTO, CommentsEntity.class);
        commentsEntity.article = articleEntity;
        CommentsEntity savedComment = commentRepository.save(commentsEntity);
        return savedComment;
    }

    public List<CommentDTO> getComment(String slug){
        List<CommentsEntity> comments = commentRepository.findAllByArticle(articleRepository.findAllBySlug(slug));
        List<CommentDTO> commentDTO = new ArrayList<>();

        for(CommentsEntity commentsEntity : comments){
            commentDTO.add(modelMapper.map(commentsEntity, CommentDTO.class));
        }

        return commentDTO;
    }

    public CommentDTO deleteComment(String slug, int commentId){
        CommentsEntity deletedComment = commentRepository.findById(commentId).orElse(null);
        commentRepository.deleteByArticleAndId(articleRepository.findAllBySlug(slug), commentId);
        CommentDTO commentDTO = modelMapper.map(deletedComment, CommentDTO.class);
        return commentDTO;
    }
}
