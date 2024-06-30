package com.server.vnnews.service;

import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteArticleService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Article> getAllFavoriteArticles() {
        return favoriteRepository.findAllFavoriteArticles();
    }
}
