package com.server.vnnews.service;

import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public List<NewsFeedArticleDTO> getArticlesInNewsFeed() {
        return repository.getArticlesInNewsFeed();
    }

    public List<Article> getHomeNewsFeed() {
        return repository.findAll();
    }
}
