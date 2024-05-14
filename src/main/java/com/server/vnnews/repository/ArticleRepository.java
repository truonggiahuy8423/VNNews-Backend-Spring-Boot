package com.server.vnnews.repository;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar")
//    @Query("SELECT a, c FROM Article a OUTER JOIN a.articleCategories ac OUTER JOIN ac.category c group by a")
    List<NewsFeedArticleDTO> getArticlesInNewsFeed();
    // Các phương thức tùy chỉnh có thể được định nghĩa ở đây nếu cần
}
