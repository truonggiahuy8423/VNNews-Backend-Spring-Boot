package com.server.vnnews.repository;

import com.server.vnnews.dto.HistoryArticleDTO;
import com.server.vnnews.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface HistoryArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT new com.server.vnnews.dto.HistoryArticleDTO(a.articleId, a.title, a.description, a.thumbnail) FROM Article a WHERE a.articleId IN :articleIds")
    List<HistoryArticleDTO> findArticleByIds(@Param("articleIds") ArrayList<Long> articleIds);
}

