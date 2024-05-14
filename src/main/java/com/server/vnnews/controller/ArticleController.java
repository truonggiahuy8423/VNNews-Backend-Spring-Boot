package com.server.vnnews.controller;

import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping("/api/article/get-articles-in-news-feed")
    public List<NewsFeedArticleDTO> getAll() {
        return service.getArticlesInNewsFeed();
    }

    @GetMapping("/api/article/get-all")
    public List<NewsFeedArticleDTO> getArticlesInNewsFeed() {
        return service.getArticlesInNewsFeed();
    }
}
