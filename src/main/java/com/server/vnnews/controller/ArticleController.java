package com.server.vnnews.controller;

import com.server.vnnews.dto.*;
import com.server.vnnews.entity.Article;
import com.server.vnnews.entity.Category;
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
                                                                @RequestParam(value = "category_id", required = true) Long categoryId,
                                                                @RequestParam(value = "filter_type", required = true) int filterType,
                                                                @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            String email = AuthenticationService.getEmailFromToken(jwt);
            System.out.println(email);
        } catch (ParseException e) {
            // handle exception
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);

        }
        return new ResponseEntity<>(service.getArticlesInNewsFeed(pageIndex, categoryId, filterType), HttpStatus.OK);
    }

//    @GetMapping("/api/article/get-articles-in-news-feed")
//    public ResponseEntity<List<NewsFeedArticleDTO>> getArticles(@RequestParam(value = "page_index", required = true) int pageIndex,
//                                                                @RequestHeader("Authorization") String token) {
//        String jwt = token.substring(7);
//        try {
//            String email = AuthenticationService.getEmailFromToken(jwt);
//            System.out.println(email);
//        } catch (ParseException e) {
//            // handle exception
//            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
//
//        }
//        return new ResponseEntity<>(service.getArticlesInNewsFeed(pageIndex), HttpStatus.OK);
//    }

    @GetMapping("api/article/get-article-by-id")
    public ResponseEntity<ArticleInReadingPageDTO> getArticleById(@RequestParam(value = "article_id", required = true) long articleId, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            return new ResponseEntity<>(service.getArticleById(articleId, userIdFromToken), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
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

    @PostMapping("api/article/post-article")
    public ResponseEntity<PostArticleResponse> postArticle(@RequestBody PostArticleRequestDTO articleDTO, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            articleDTO.setUserId(userIdFromToken);
            System.out.println(userIdFromToken);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return  new ResponseEntity<>(new PostArticleResponse(service.postArticle(articleDTO), articleDTO.rvPosition), HttpStatus.OK);
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
        System.out.println(likeCommentDTO.getCommentId());
        LikeCommentDTO result = service.saveLikeComment(likeCommentDTO);
        System.out.println(result.getSuccess());
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        System.out.println(likeCommentDTO.getCommentId());
        LikeCommentDTO result = service.unlikeComment(likeCommentDTO);
        System.out.println(result.getSuccess());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/api/article/get-all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = service.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/get-articles-in-user-info")
    public ResponseEntity<List<ArticleUserInfoDTO>> getArticlesUserInfo(@RequestParam(value = "userId", required = true) Long userId,
                                                        @RequestParam(value = "page_index", required = true) int pageIndex,
                                                        @RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
        return new ResponseEntity<>(service.getArticlesUserInfo(userId, pageIndex), HttpStatus.OK);
    }

    @PostMapping("/api/article/save-bookmark")
    public ResponseEntity<BookmarkRequest> saveBookmark(@RequestBody BookmarkRequest request, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            request.setUserId(userIdFromToken);
            return new ResponseEntity<>(service.saveBookmark(request), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }
    @PostMapping("/api/article/abort-bookmark")
    public ResponseEntity<BookmarkRequest> abortBookmark(@RequestBody BookmarkRequest request, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            request.setUserId(userIdFromToken);
            return new ResponseEntity<>(service.abortBookmark(request), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }

    @PostMapping("/api/article/save-see-later")
    public ResponseEntity<BookmarkRequest> saveSeeLater(@RequestBody BookmarkRequest request, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            request.setUserId(userIdFromToken);
            return new ResponseEntity<>(service.saveSeeLater(request), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }
    @PostMapping("/api/article/abort-see-later")
    public ResponseEntity<BookmarkRequest> abortSeeLater(@RequestBody BookmarkRequest request, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            request.setUserId(userIdFromToken);
            return new ResponseEntity<>(service.abortSeeLater(request), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }
    @PostMapping("/api/article/view-article")
    public ResponseEntity<BookmarkRequest> viewArticle(@RequestBody BookmarkRequest request, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        try {
            Long userIdFromToken = AuthenticationService.getUserIdFromToken(jwt);
            request.setUserId(userIdFromToken);
            return new ResponseEntity<>(service.viewArticle(request), HttpStatus.OK);
        } catch (ParseException e) {
            throw new AppRuntimeException(AppRuntimeException.AUTHENTICATION_FAILED_MESSAGE, AppRuntimeException.AUTHENTICATION_FAILED);
        }
    }
    @GetMapping("/api/article/search")
    public List<Article> searchArticles(@RequestParam("search_query") String keyword) {
        return service.searchArticles(keyword);
    }
}
