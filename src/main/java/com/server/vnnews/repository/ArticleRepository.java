package com.server.vnnews.repository;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.dto.UserCommentDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


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
    List<NewsFeedArticleDTO> getArticlesInNewsFeed(Pageable pageable);
    // Các phương thức tùy chỉnh có thể được định nghĩa ở đây nếu cần

//    @Query("SELECT a.articleId, a.title, a.description, a.thumbnailName, a.createTime, a.modifyTime, " +
//            "COUNT(v), COUNT(c), u.userId, u.name, COUNT(f) " +
//            "FROM Article a " +
//            "LEFT JOIN a.views v " +
//            "LEFT JOIN a.comments c " +
//            "LEFT JOIN a.user u " +
//            "LEFT JOIN u.followers f " +
//            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar")
    @Query("SELECT a.articleId, ac.id FROM Article a JOIN a.articleCategories ac GROUP BY a, ac")
    List<Object[]> test();

    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "WHERE a.articleId = :articleId " +  // Thêm điều kiện articleId vào truy vấn
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar")
    NewsFeedArticleDTO getArticleById(@Param("articleId") Long articleId);


    @Query("SELECT new com.server.vnnews.dto.UserCommentDTO(c.commentId, c.content, c.createTime, c.modifyTime, COUNT(l), a.articleId, p.commentId, u.userId, u.name, u.avatar) " +
            "FROM Comment c " +
            "LEFT JOIN c.user u " +
            "LEFT JOIN c.article a " +
            "LEFT JOIN c.parentComment p " +
            "LEFT JOIN c.likeComments l " +
            "WHERE c.article.articleId = :articleId " +  // Thêm điều kiện articleId vào truy vấn
            "GROUP BY c.commentId, c.content, c.createTime, c.modifyTime, a.articleId, p.commentId, u.userId, u.name, u.avatar")
    List<UserCommentDTO> getCommentsByArticleId(@Param("articleId") Long articleId, Pageable pageable);


    @Query("SELECT new com.server.vnnews.dto.UserCommentDTO(c.commentId, c.content, c.createTime, c.modifyTime, COUNT(l), a.articleId, p.commentId, u.userId, u.name, u.avatar) " +
            "FROM Comment c " +
            "LEFT JOIN c.user u " +
            "LEFT JOIN c.article a " +
            "LEFT JOIN c.parentComment p " +
            "LEFT JOIN c.likeComments l " +
            "WHERE c.commentId= :commentId " +  // Thêm điều kiện articleId vào truy vấn
            "GROUP BY c.commentId, c.content, c.createTime, c.modifyTime, a.articleId, p.commentId, u.userId, u.name, u.avatar")
    UserCommentDTO getCommentById(@Param("commentId") Long commentId);


}
