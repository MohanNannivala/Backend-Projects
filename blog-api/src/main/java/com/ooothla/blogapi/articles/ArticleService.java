package com.ooothla.blogapi.articles;

import com.ooothla.blogapi.commons.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public final ArticleRepository articleRepository;
    public final ModelMapper modelMapper;
    public final SlugGenerator slugGenerator;

    ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper, SlugGenerator slugGenerator){
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.slugGenerator = slugGenerator;
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO){
        ArticleEntity articleEntity = modelMapper.map(articleDTO, ArticleEntity.class);
        articleEntity.slug = slugGenerator.generateSlug(articleEntity.title);
        ArticleEntity savedArticle = articleRepository.save(articleEntity);
        ArticleDTO response = modelMapper.map(savedArticle, ArticleDTO.class);

        return response;
    }

    public List<ArticleDTO> getArticle(){

        List<ArticleEntity> articleEntities = articleRepository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (ArticleEntity a: articleEntities) {
            articleDTOS.add(modelMapper.map(a, ArticleDTO.class));
        }

        return articleDTOS;
    }

    public ArticleDTO getAllBySlug(String slug){

        ArticleEntity articleEntity = articleRepository.findAllBySlug(slug);
        ArticleDTO articleDTO = modelMapper.map(articleEntity, ArticleDTO.class);

        return articleDTO;
    }

    public ArticleDTO updateArtical(String slug, ArticleDTO articleDTO){

        ArticleEntity articleEntity = articleRepository.findAllBySlug(slug);

        articleEntity.body = articleDTO.body;
        articleEntity.title = articleDTO.title;
        articleEntity.subtitle = articleDTO.subtitle;

        ArticleEntity response = articleRepository.save(articleEntity);

        ArticleDTO responseDTO = modelMapper.map(articleEntity, ArticleDTO.class);

        return responseDTO;
    }

}
