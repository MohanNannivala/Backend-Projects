package com.ooothla.blogapi.articles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    public final ArticleService articleService;

    ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("")
    public ResponseEntity<ArticleDTO> createArtical(@RequestBody ArticleDTO articleDTO){
        ArticleDTO response = articleService.createArticle(articleDTO);
        return ResponseEntity.created(URI.create("/article/"+response.slug)).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleDTO>> getArticles(){
        return ResponseEntity.ok(articleService.getArticle());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> getArticlBySlug(@PathVariable String slug){
        return ResponseEntity.ok(articleService.getAllBySlug(slug));
    }

    @PatchMapping("/{slug}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable String slug, @RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok().body(articleService.updateArtical(slug, articleDTO));
    }
}
