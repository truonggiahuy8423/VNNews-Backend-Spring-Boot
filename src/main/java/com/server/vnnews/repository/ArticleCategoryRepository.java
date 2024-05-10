package com.server.vnnews.repository;

import com.server.vnnews.entity.ArticleCategory;
import com.server.vnnews.entity.composite.ArticleCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, ArticleCategoryId> {
    // Các phương thức tùy chỉnh có thể được định nghĩa ở đây
}
