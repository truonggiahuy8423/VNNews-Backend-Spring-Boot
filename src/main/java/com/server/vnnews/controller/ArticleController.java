package com.server.vnnews.controller;

import com.server.vnnews.dto.ArticleInReadingPageDTO;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.service.ArticleService;
import com.server.vnnews.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;


    @GetMapping("/api/article/get-articles-in-news-feed")
    public ResponseEntity<List<NewsFeedArticleDTO>> getArticles(@RequestParam(value = "page_index", required = true) int pageIndex,
                                                           @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            String email = AuthenticationService.getEmailFromToken(jwt);
            System.out.println(email);
        } catch (ParseException e) {
            // handle exception
            System.out.println("Lỗi");

        }
        return new ResponseEntity<>(service.getArticlesInNewsFeed(pageIndex), HttpStatus.OK);
    }

    @GetMapping("/api/article/test")
    public ResponseEntity<List<Object[]>> test() {
        return new ResponseEntity<>(service.test(), HttpStatus.OK);
    }

    @GetMapping("api/article/get-article-by-id")
    public ResponseEntity<ArticleInReadingPageDTO> getArticleById(@RequestParam(value = "article_id", required = true) long articleId) {
        return new ResponseEntity<>(service.getArticleById(articleId), HttpStatus.OK);
    }

}
