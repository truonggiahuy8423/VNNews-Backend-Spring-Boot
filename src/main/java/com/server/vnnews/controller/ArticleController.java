package com.server.vnnews.controller;

import com.server.vnnews.dto.*;
import com.server.vnnews.entity.Article;
import com.server.vnnews.exception.AppRuntimeException;
import com.server.vnnews.service.ArticleService;
import com.server.vnnews.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping("/api/article/test")
    public ResponseEntity<List<Object[]>> test() {
        return new ResponseEntity<>(service.test(), HttpStatus.OK);
    }

    @GetMapping("/api/article/get-articles-in-news-feed")
    public ResponseEntity<List<NewsFeedArticleDTO>> getArticles(@RequestParam(value = "page_index", required = true) int pageIndex,
                                                                @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            String email = AuthenticationService.getEmailFromToken(jwt);
            System.out.println(email);
        } catch (ParseException e) {
            // handle exception
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);

        }
        return new ResponseEntity<>(service.getArticlesInNewsFeed(pageIndex), HttpStatus.OK);
    }

    @GetMapping("api/article/get-article-by-id")
    public ResponseEntity<ArticleInReadingPageDTO> getArticleById(@RequestParam(value = "article_id", required = true) long articleId) {
        return new ResponseEntity<>(service.getArticleById(articleId), HttpStatus.OK);
    }

    @GetMapping("api/article/get-comments-by-article-id")
    public ResponseEntity<CommentLoadingResponse> getCommentsByArticleId(@RequestParam(value = "article_id", required = true) long articleId, @RequestParam(value = "page_index", required = true) int pageIndex) {
        return new ResponseEntity<>(service.getCommentsByArticleId(articleId, pageIndex), HttpStatus.OK);
    }

    @PostMapping("api/article/post-comment")
    public ResponseEntity<UserCommentDTO> postComment(@RequestBody CommentPostingRequest commentPostingRequest, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            commentPostingRequest.setUserId(userIdFromToken);
            System.out.println(userIdFromToken);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(service.postComment(commentPostingRequest), HttpStatus.OK);
    }

    @GetMapping("/get-articles-in-scroll-page")
    public ResponseEntity<List<ArticleScrollPageDTO>> getArticlesInScrollPage(@RequestParam(value = "page_index", required = true) int pageIndex,
                                                                              @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            String email = AuthenticationService.getEmailFromToken(jwt);
            System.out.println(email);
        } catch (ParseException e) {
            // handle exception
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(service.getArticlesScrollPage(pageIndex), HttpStatus.OK);
    }

    @PostMapping("api/article/like-comment")
    public ResponseEntity<LikeCommentDTO> likeComment(@RequestBody LikeCommentDTO likeCommentDTO, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            likeCommentDTO.setUserId(userIdFromToken);
            System.out.println(userIdFromToken);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(service.saveLikeComment(likeCommentDTO), HttpStatus.OK);
    }

    @PostMapping("api/article/unlike-comment")
    public ResponseEntity<LikeCommentDTO> unlikeComment(@RequestBody LikeCommentDTO likeCommentDTO, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            likeCommentDTO.setUserId(userIdFromToken);
            System.out.println(userIdFromToken);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(service.unlikeComment(likeCommentDTO), HttpStatus.OK);
    }
}