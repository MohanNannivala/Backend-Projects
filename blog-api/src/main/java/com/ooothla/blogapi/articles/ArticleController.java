package com.ooothla.blogapi.articles;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    public final ArticleService articleService;

    ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO, @AuthenticationPrincipal Integer userId){
        ArticleDTO response = articleService.createArticle(articleDTO, userId);
        return ResponseEntity.created(URI.create("/articles/"+response.slug)).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleDTO>> getArticles(){
        return ResponseEntity.ok(articleService.getArticle());
    }

    @GetMapping("/user")
    public ResponseEntity<List<ArticleDTO>> getAllByUserId(@AuthenticationPrincipal Integer userId){
        return ResponseEntity.ok(articleService.getAllByUserId(userId));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> getArticleBySlug(@PathVariable String slug){
        return ResponseEntity.ok(articleService.getAllBySlug(slug));
    }

    @PatchMapping("/{slug}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable String slug, @RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok().body(articleService.updateArticle(slug, articleDTO));
    }
}
