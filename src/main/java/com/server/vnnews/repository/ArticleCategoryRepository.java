package com.server.vnnews.repository;

import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.ArticleCategory;
import com.server.vnnews.entity.composite.ArticleCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, ArticleCategoryId> {
    // Các phương thức tùy chỉnh có thể được định nghĩa ở đây


}
