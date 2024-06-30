package com.server.vnnews.controller;

import com.server.vnnews.entity.Article;
import com.server.vnnews.service.FavoriteArticleService;
import com.server.vnnews.service.SeeLaterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/see-later")
public class SeeLaterArticleController {

    @Autowired
    private SeeLaterArticleService seeLaterArticleService;

    @GetMapping("/articles")
    public List<Article> getFavoriteArticles() {
        return seeLaterArticleService.getAllSeeLaterArticles();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
