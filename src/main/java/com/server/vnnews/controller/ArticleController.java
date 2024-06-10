package com.server.vnnews.controller;

import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.ArticleScrollPageDTO;
import com.server.vnnews.service.ArticleService;
import com.server.vnnews.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<NewsFeedArticleDTO>> getAll() {
        return new ResponseEntity<>(service.getArticlesInNewsFeed(), HttpStatus.OK);
    }

    @GetMapping("/api/article/get-all")
    public List<NewsFeedArticleDTO> getArticlesInNewsFeed() {
        return service.getArticlesInNewsFeed();
    }

    @GetMapping("/get-articles-in-scroll-page")
    public ResponseEntity<List<ArticleScrollPageDTO>> getArticlesInScrollPage(@RequestParam(value = "page_index", required = true) int pageIndex,
                                                                              @RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        try {
            String email = AuthenticationService.getEmailFromToken(jwt);
            System.out.println(email);
        } catch (ParseException e) {
            // handle exception
            System.out.println("Lỗi");

        }
        return new ResponseEntity<>(service.getArticlesScrollPage(pageIndex), HttpStatus.OK);
    }
}
