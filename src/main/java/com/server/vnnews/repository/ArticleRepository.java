package com.server.vnnews.repository;
import com.server.vnnews.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Các phương thức tùy chỉnh có thể được định nghĩa ở đây nếu cần
}
