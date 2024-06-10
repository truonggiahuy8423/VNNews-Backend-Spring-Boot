package com.server.vnnews.repository;

import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.BodyItemDTO;
import com.server.vnnews.entity.BodyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BodyItemRepository extends JpaRepository<BodyItem, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần
    @Query("SELECT new com.server.vnnews.entity.BodyItem(b.bodyItemId, b.imageName, b.dataImage, b.content, b.ordinalNumber, b.bodyTitle) " +
            "FROM Article a " +
            "LEFT JOIN a.bodyItems b " +
            "WHERE a.articleId = :articleId")
    List<BodyItem> getArticleBodyItemsByArticleId(@Param("articleId") Long articleId);
    
    @Query("SELECT bi " +
            "FROM BodyItem bi " +
            "WHERE bi.article.articleId = :articleId")
    public List<BodyItem> findByArticleId(@Param("articleId") Long articleId);
}
