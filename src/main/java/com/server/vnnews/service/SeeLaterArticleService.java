package com.server.vnnews.service;

import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.SeeLaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeeLaterArticleService {

    @Autowired
    private SeeLaterRepository seeLaterRepository;

    public List<Article> getAllSeeLaterArticles() {
        return seeLaterRepository.findAllSeeLaterArticles();
    }
}
