package com.server.vnnews.service;

import com.server.vnnews.dto.BodyItemDTO;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.ArticleScrollPageDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.ArticleRepository;
import com.server.vnnews.repository.BodyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    @Autowired
    private BodyItemRepository bodyItemRepository;

    public List<NewsFeedArticleDTO> getArticlesInNewsFeed() {
        return repository.getArticlesInNewsFeed();
    }

    public List<Article> getHomeNewsFeed() {
        return repository.findAll();
    }

    public List<ArticleScrollPageDTO> getArticlesScrollPage(){
        List<ArticleScrollPageDTO> articleScrollPageDTOList = repository.getArticlesScrollPage();
        return articleScrollPageDTOList.stream().map(dto -> {
            List<BodyItemDTO> bodyItemDTOList = bodyItemRepository.findByArticleId(dto.getArticleId()).stream()
                    .map(bodyItem -> new BodyItemDTO(bodyItem.getBodyItemId(), bodyItem.getImageName(), bodyItem.getContent(), bodyItem.getDataImage() , bodyItem.getBodyTitle(), bodyItem.getOrdinalNumber()))
                    .collect(Collectors.toList());
            dto.setBodyItemList(bodyItemDTOList);
            return dto;
        }).collect(Collectors.toList());
    }
}
