package com.server.vnnews.service;

import com.server.vnnews.dto.*;
import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.Comment;
import com.server.vnnews.entity.User;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.exception.DatabaseException;
import com.server.vnnews.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import com.server.vnnews.dto.NewsFeedArticleDTO;
import com.server.vnnews.entity.Article;
import com.server.vnnews.repository.ArticleRepository;
import com.server.vnnews.repository.BodyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BodyItemRepository bodyItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    public CommentLoadingResponse getCommentsByArticleId(Long articleId, Integer pageIndex) {
        Long commentCount = commentRepository.count();
        Integer maxPageIndex = (commentCount.intValue())/10;



        Pageable pageable = PageRequest.of(pageIndex - 1, 10); // pageIndex - 1 vì Spring Data JPA sử dụng chỉ mục trang từ 0
        return new CommentLoadingResponse(articleRepository.getCommentsByArticleId(articleId, pageable), commentCount, maxPageIndex);
    }
    public List<Object[]> test() {
        return articleRepository.test();
    }

    @Transactional
    public UserCommentDTO postComment(CommentPostingRequest commentPostingRequest) {
        Comment comment = new Comment();

        comment.setContent(commentPostingRequest.getContent());
        // Đặt thời gian tạo và sửa đổi
        comment.setCreateTime(commentPostingRequest.getCreateTime());
        comment.setModifyTime(commentPostingRequest.getModifyTime());

        // Tạo một đối tượng Article chỉ bằng cách đặt articleId
        Article article = new Article();
        article.setArticleId(commentPostingRequest.getArticleId()); // Giả sử articleId là thuộc tính trong UserCommentDTO

        User user = new User();
        user.setUserId(commentPostingRequest.getUserId()); // Giả sử articleId là thuộc tính trong UserCommentDTO

        if (commentPostingRequest.getParentCommentId() != null) {
            Comment parent = new Comment();
            parent.setCommentId(commentPostingRequest.getParentCommentId());
        }
        // Gán đối tượng Article cho comment
        comment.setArticle(article);
        comment.setUser(user);


        Comment insertedComment = null;
         try {
            insertedComment = commentRepository.save(comment);
            // Hãy bỏ dữ liệu từ Comment sang commentDTO
             User user2 = userRepository.findByUserId(commentPostingRequest.getUserId());
             UserCommentDTO commentDTO = new UserCommentDTO();
             System.out.println(user2.getName());

             commentDTO.setCommentId(insertedComment.getCommentId());
             commentDTO.setContent(commentPostingRequest.getContent());
             commentDTO.setCreateTime(commentPostingRequest.getCreateTime());
             commentDTO.setModifyTime(commentPostingRequest.getModifyTime());
             commentDTO.setLikeCommentCount((long)0);
             commentDTO.setArticleId(commentPostingRequest.getArticleId());
             commentDTO.setParentCommentId(commentDTO.getParentCommentId());
             commentDTO.setUserId(commentPostingRequest.getUserId());
             commentDTO.setName(user2.getName());
             commentDTO.setAvatar(user2.getAvatar());

             return commentDTO;
         } catch (DataIntegrityViolationException e) {
             throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
         } catch (Exception e) {
             throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
         }
    }

    public List<ArticleScrollPageDTO> getArticlesScrollPage(int pageIndex){
        Pageable pageable =  PageRequest.of(pageIndex - 1, 5); // pageIndex - 1 vì Spring Data JPA sử dụng chỉ mục trang từ 0
        List<ArticleScrollPageDTO> articleScrollPageDTOList = articleRepository.getArticlesScrollPage(pageable);
        return articleScrollPageDTOList.stream().map(dto -> {
            List<BodyItemDTO> bodyItemDTOList = bodyItemRepository.getArticleBodyItemsByArticleId(dto.getArticleId()).stream()
                    .map(bodyItem -> new BodyItemDTO(bodyItem.getBodyItemId(), null, bodyItem.getContent(), null , bodyItem.getBodyTitle(), bodyItem.getOrdinalNumber()))
                    .collect(Collectors.toList());
            dto.setBodyItemList(bodyItemDTOList);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ArticleUserInfoDTO> getArticlesUserInfo(Long userId, int pageIndex){
        Pageable pageable =  PageRequest.of(pageIndex - 1, 10);
        return articleRepository.getArticlesUserInfo(userId, pageable);
    }
}
