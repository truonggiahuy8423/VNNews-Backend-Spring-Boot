package com.server.vnnews.service;

import com.server.vnnews.dto.ArticleInReadingPageDTO;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.repository.ArticleCategoryRepository;
import com.server.vnnews.repository.ArticleRepository;
import com.server.vnnews.repository.BodyItemRepository;
import com.server.vnnews.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;



import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BodyItemRepository bodyItemRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    public List<NewsFeedArticleDTO> getArticlesInNewsFeed(int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 10); // pageIndex - 1 vì Spring Data JPA sử dụng chỉ mục trang từ 0
        return articleRepository.getArticlesInNewsFeed(pageable);
    }

    public ArticleInReadingPageDTO getArticleById(Long articleId) {
        ArticleInReadingPageDTO article = new ArticleInReadingPageDTO(
                articleRepository.getArticleById(articleId),
                bodyItemRepository.getArticleBodyItemsByArticleId(articleId),
                articleCategoryRepository.getArticleCategoriesByArticleId(articleId));

        return article;
    }

    public List<Object[]> test() {
        return articleRepository.test();
    }
}
