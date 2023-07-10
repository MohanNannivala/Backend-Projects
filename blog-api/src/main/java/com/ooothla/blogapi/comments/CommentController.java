package com.ooothla.blogapi.comments;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Data
@RequestMapping("/article")
public class CommentController {
    public final CommentService commentService;
    public final ModelMapper modelMapper;


    CommentController(CommentService commentService, ModelMapper modelMapper){
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{slug}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable String slug, @RequestBody CommentDTO comment){
        CommentsEntity commentsEntity = commentService.createComment(slug, comment);
        CommentDTO savedComment = modelMapper.map(commentsEntity, CommentDTO.class);
        return ResponseEntity.created(URI.create("/article/"+slug+"/comments/"+commentsEntity.getId())).body(savedComment);
    }

    @GetMapping("/{slug}/comments")
    public ResponseEntity<List<CommentDTO>> getComment(@PathVariable String slug){
        return ResponseEntity.ok(commentService.getComment(slug));
    }

    @DeleteMapping("/{slug}/comments/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable String slug, @PathVariable int commentId){
        return ResponseEntity.accepted().body(commentService.deleteComment(slug, commentId));
    }
}
