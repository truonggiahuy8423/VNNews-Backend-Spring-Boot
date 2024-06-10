package com.server.vnnews.service;

import com.server.vnnews.dto.BodyItemDTO;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.ArticleScrollPageDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.ArticleRepository;
import com.server.vnnews.repository.BodyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ArticleScrollPageDTO> getArticlesScrollPage(int pageIndex){
        Pageable pageable =  PageRequest.of(pageIndex - 1, 5); // pageIndex - 1 vì Spring Data JPA sử dụng chỉ mục trang từ 0
        List<ArticleScrollPageDTO> articleScrollPageDTOList = repository.getArticlesScrollPage(pageable);
        return articleScrollPageDTOList.stream().map(dto -> {
            List<BodyItemDTO> bodyItemDTOList = bodyItemRepository.findByArticleId(dto.getArticleId()).stream()
                    .map(bodyItem -> new BodyItemDTO(bodyItem.getBodyItemId(), null, bodyItem.getContent(), null , bodyItem.getBodyTitle(), bodyItem.getOrdinalNumber()))
                    .collect(Collectors.toList());
            dto.setBodyItemList(bodyItemDTOList);
            return dto;
        }).collect(Collectors.toList());
    }
}
