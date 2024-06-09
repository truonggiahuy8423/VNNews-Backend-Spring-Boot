package com.server.vnnews.controller;

import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.ArticleScrollPageDTO;
import com.server.vnnews.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping("/api/article/get-articles-in-news-feed")
    public ResponseEntity<List<NewsFeedArticleDTO>> getAll() {
        return new ResponseEntity<>(service.getArticlesInNewsFeed(), HttpStatus.OK);
    }

    @GetMapping("/api/article/get-all")
    public List<NewsFeedArticleDTO> getArticlesInNewsFeed() {
        return service.getArticlesInNewsFeed();
    }

    @GetMapping("/get-articles-in-scroll-page")
    public List<ArticleScrollPageDTO> getArticlesInScrollPage(){
        return service.getArticlesScrollPage();
    }
}
