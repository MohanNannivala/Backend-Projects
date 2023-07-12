package com.ooothla.blogapi.articles;

import com.ooothla.blogapi.commons.SlugGenerator;
import com.ooothla.blogapi.users.UserRepository;
import com.ooothla.blogapi.users.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public final ArticleRepository articleRepository;
    public final ModelMapper modelMapper;
    public final SlugGenerator slugGenerator;
    public final UserRepository userRepository;

    ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper, SlugGenerator slugGenerator, UserRepository userRepository){
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.slugGenerator = slugGenerator;
        this.userRepository = userRepository;
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO, Integer userId){
        ArticleEntity articleEntity = modelMapper.map(articleDTO, ArticleEntity.class);

        articleEntity.setAuthor(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId)));
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

    public ArticleDTO updateArticle(String slug, ArticleDTO articleDTO){

        ArticleEntity articleEntity = articleRepository.findAllBySlug(slug);

        articleEntity.body = articleDTO.body;
        articleEntity.title = articleDTO.title;
        articleEntity.subtitle = articleDTO.subtitle;

        ArticleEntity response = articleRepository.save(articleEntity);

        ArticleDTO responseDTO = modelMapper.map(articleEntity, ArticleDTO.class);

        return responseDTO;
    }

    public List<ArticleDTO> getAllByUserId(int userId){

        List<ArticleEntity> articleEntities = articleRepository.findAllByAuthorId(userId);

        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (ArticleEntity a: articleEntities) {
            articleDTOS.add(modelMapper.map(a, ArticleDTO.class));
        }

        return articleDTOS;
    }

}
