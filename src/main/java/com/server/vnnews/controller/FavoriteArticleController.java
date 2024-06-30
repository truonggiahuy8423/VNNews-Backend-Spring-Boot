package com.server.vnnews.controller;

import com.server.vnnews.dto.FavoriteArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.service.FavoriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteArticleController {

    @Autowired
    private FavoriteArticleService favoriteService;

    @GetMapping("/articles")
    public List<Article> getFavoriteArticles() {
        return favoriteService.getAllFavoriteArticles();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }

}
