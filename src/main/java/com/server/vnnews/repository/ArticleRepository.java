package com.server.vnnews.repository;
import com.server.vnnews.dto.*;
import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Article findArticleByArticleId(Long articleId);

    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f), COUNT(fa)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites fa " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar " +
            "ORDER BY a.createTime DESC")
    List<NewsFeedArticleDTO> getArticlesInNewsFeedWithNewestFilter(Pageable pageable);


    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f), COUNT(fa)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites fa " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "LEFT JOIN a.articleCategories ac " +
            "WHERE ac.id.categoryId = :categoryId " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar " +
            "ORDER BY a.createTime DESC")
    List<NewsFeedArticleDTO> getArticlesInNewsFeedWithCategoryIdAndNewestFilter(Pageable pageable, @Param("categoryId")  Long categoryId);

    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f), COUNT(fa)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v ON v.time >= :startTime AND v.time < :endTime " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites fa " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar " +
            "ORDER BY COUNT(v) DESC")
    List<NewsFeedArticleDTO> getArticlesInNewsFeedWithHighestViewFilter(@Param("startTime") Date startTime, @Param("endTime") Date endTime, Pageable pageable);

    @Query("SELECT new com.server.vnnews.dto.NewsFeedArticleDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f), COUNT(fa)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v ON v.article.articleId = a.articleId AND v.time >= :startTime AND v.time < :endTime " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites fa " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "LEFT JOIN a.articleCategories ac " +
            "WHERE ac.id.categoryId = :categoryId " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar " +
            "ORDER BY COUNT(v) DESC")
    List<NewsFeedArticleDTO> getArticlesInNewsFeedWithCategoryIdAndHighestViewFilter(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("categoryId") Long categoryId, Pageable pageable);


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
            "COUNT(v), COUNT(c), u.userId, u.name, u.avatar, COUNT(f), COUNT(fa)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites fa " +
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


    @Query("SELECT new com.server.vnnews.dto.ArticleScrollPageDTO(" +
            "a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, " +
            "COUNT(v), COUNT(c), COUNT(s),u.userId, u.name, u.avatar, COUNT(f)) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.comments c " +
            "LEFT JOIN a.favorites s " +
            "LEFT JOIN a.user u " +
            "LEFT JOIN u.followers f " +
            "GROUP BY a.articleId, a.title, a.description, a.thumbnail, a.thumbnailName, a.createTime, a.modifyTime, u.userId, u.name, u.avatar")
    List<ArticleScrollPageDTO> getArticlesScrollPage(Pageable pageable);


    @Query("SELECT new com.server.vnnews.dto.ArticleUserInfoDTO(" +
            "a.articleId, a.title, a.createTime, a.modifyTime, " +
            "COUNT(v), u.userId, a.thumbnail) " +
            "FROM Article a " +
            "LEFT JOIN a.views v " +
            "LEFT JOIN a.user u " +
            "Where u.userId = :userId " +
            "GROUP BY a.articleId, a.title, a.createTime, a.modifyTime, u.userId, a.thumbnail")
    List<ArticleUserInfoDTO> getArticlesUserInfo(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT Count(a.articleId)" +
            "From Article a " +
            "LEFT JOIN a.user u " +
            "Where u.userId= :userId")
    Long getNoPostByUserId(@Param("userId") Long userId);
}
