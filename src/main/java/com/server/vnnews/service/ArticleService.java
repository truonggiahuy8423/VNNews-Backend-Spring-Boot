package com.server.vnnews.service;

import com.server.vnnews.common.FilterType;
import com.server.vnnews.dto.*;
import com.server.vnnews.entity.*;
import com.server.vnnews.entity.composite.ArticleCategoryId;
import com.server.vnnews.entity.composite.FavoriteId;
import com.server.vnnews.entity.composite.LikeCommentId;
import com.server.vnnews.entity.composite.SeeLaterId;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
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
    private LikeCommentRepository likeCommentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private SeeLaterRepository seeLaterRepository;

    public List<NewsFeedArticleDTO> getArticlesInNewsFeed(int pageIndex, Long categoryId, int filterType) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 10); // pageIndex - 1 vì Spring Data JPA sử dụng chỉ mục trang từ 0
        Date startTime;
        Date endTime;

        // Calculate start and end time based on filter type
        Calendar calendar = Calendar.getInstance();
        switch (filterType) {
            case FilterType.TODAY:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                endTime = calendar.getTime();
                break;
            case FilterType.WEEK:
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                calendar.add(Calendar.MILLISECOND, -1);
                endTime = calendar.getTime();
                break;
            case FilterType.MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();
                calendar.add(Calendar.MONTH, 1);
                calendar.add(Calendar.MILLISECOND, -1);
                endTime = calendar.getTime();
                break;
            default:
                startTime = null;
                endTime = null;
                break;
        }

        if (categoryId == -1) {
            switch (filterType) {
                case FilterType.NEWEST:
                    return articleRepository.getArticlesInNewsFeedWithNewestFilter(pageable);
                case FilterType.EXPLORE:
                    return articleRepository.getArticlesInNewsFeedWithNewestFilter(pageable);
                case FilterType.TODAY:
                case FilterType.WEEK:
                case FilterType.MONTH:
                    if (startTime != null && endTime != null) {
                        return articleRepository.getArticlesInNewsFeedWithHighestViewFilter(startTime, endTime, pageable);
                    }
                    break;
            }
        } else {
            switch (filterType) {
                case FilterType.NEWEST:
                    return articleRepository.getArticlesInNewsFeedWithCategoryIdAndNewestFilter(pageable, categoryId);
                case FilterType.EXPLORE:
                    return articleRepository.getArticlesInNewsFeedWithCategoryIdAndNewestFilter(pageable, categoryId);
                case FilterType.TODAY:
                case FilterType.WEEK:
                case FilterType.MONTH:
                    if (startTime != null && endTime != null) {
                        return articleRepository.getArticlesInNewsFeedWithCategoryIdAndHighestViewFilter(startTime, endTime, categoryId, pageable);
                    }
                    break;
            }
        }
        return articleRepository.getArticlesInNewsFeedWithNewestFilter(pageable);
    }


    public ArticleInReadingPageDTO getArticleById(Long articleId, Long userId) {
        ArticleInReadingPageDTO article = new ArticleInReadingPageDTO(
                articleRepository.getArticleById(articleId),
                bodyItemRepository.getArticleBodyItemsByArticleId(articleId),
                articleCategoryRepository.getArticleCategoriesByArticleId(articleId),
                favoriteRepository.findById(new FavoriteId(userId, articleId)).isPresent() ? 1 : 0,
                seeLaterRepository.findById(new SeeLaterId(userId, articleId)).isPresent() ? 1 : 0
        );


        System.out.println(article.getIsSaved() + " " + article.getIsSeeLater());

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

    @Transactional
    public LikeCommentDTO saveLikeComment(LikeCommentDTO likeCommentDTO) {
        LikeComment likeComment = new LikeComment();

        likeComment.setTime(likeCommentDTO.getTime());
        likeComment.setId(new LikeCommentId(likeCommentDTO.getUserId(), likeCommentDTO.getCommentId()));
        likeComment.setComment(commentRepository.findCommentByCommentId(likeCommentDTO.getCommentId()));
        likeComment.setUser(userRepository.findByUserId(likeCommentDTO.getUserId()));

        try {
            LikeComment insertedLikeComment = likeCommentRepository.save(likeComment);
            if (insertedLikeComment == null) {
                throw new RuntimeException("Inserted LikeComment is null");
            }
            System.out.println("inserted:" + insertedLikeComment.getId().getCommentId());

            LikeCommentDTO commentDTO = new LikeCommentDTO();
            commentDTO.setCommentId(insertedLikeComment.getId().getCommentId());
            commentDTO.setUserId(insertedLikeComment.getId().getUserId());
            commentDTO.setTime(insertedLikeComment.getTime());

            return commentDTO;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }


    @Transactional
    public LikeCommentDTO unlikeComment(LikeCommentDTO likeCommentDTO) {
        LikeCommentId likeCommentId = new LikeCommentId(likeCommentDTO.getUserId(), likeCommentDTO.getCommentId());

        try {
            likeCommentRepository.deleteById(likeCommentId);

            return likeCommentDTO;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Long postArticle(PostArticleRequestDTO articleDTO) {
        System.out.println(articleDTO);
        Article article = new Article();

        article.setArticleId(articleDTO.getArticleId());
        article.setCreateTime(articleDTO.getCreateTime());
        article.setModifyTime(articleDTO.getModifyTime());
        article.setDescription(articleDTO.getDescription());
        article.setTitle(articleDTO.getTitle());

        User u = new User();
        u.setUserId(articleDTO.getUserId());
        article.setUser(u);


        // Assuming you have a utility to convert String to byte[] for the thumbnail
        article.setThumbnail(convertThumbnailStringToBytes(articleDTO.getThumbnail()));
        article.setThumbnailName(articleDTO.getThumbnailName());

        try {
            article = articleRepository.save(article);
            List<Category> categories = articleDTO.getCategories();
            for (int i = 0; i<categories.size();i++) {
                ArticleCategory ac = new ArticleCategory();
                ac.setId(new ArticleCategoryId(article.getArticleId(), categories.get(i).getCategoryId()));
                ac.setArticle(articleRepository.findArticleByArticleId(article.getArticleId()));
                ac.setCategory(categoryRepository.findCategoryByCategoryId(categories.get(i).getCategoryId()));
                articleCategoryRepository.save(ac);
            }

            List<BodyItemRequest> items = articleDTO.getBodyItemList();
            for (int i = 0; i<items.size();i++) {
                BodyItemRequest itemRequest = items.get(i);
                BodyItem item = new BodyItem(null, itemRequest.getImageName(), convertThumbnailStringToBytes(itemRequest.getDataImage()), itemRequest.getContent(), itemRequest.getOrdinalNumber(), itemRequest.getBodyTitle(), article) ;
                bodyItemRepository.save(item);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
        return article.getArticleId();
    }

    private byte[] convertThumbnailStringToBytes(String thumbnail) {
        // Loại bỏ bất kỳ ký tự không hợp lệ nào từ chuỗi Base64
        String cleanThumbnail = thumbnail.replaceAll("[^A-Za-z0-9+/=]", "");

        // Giải mã chuỗi Base64 đã được làm sạch
        return Base64.getDecoder().decode(cleanThumbnail);
    }


    public List<ArticleUserInfoDTO> getArticlesUserInfo(Long userId, int pageIndex){
        Pageable pageable =  PageRequest.of(pageIndex - 1, 10);
        return articleRepository.getArticlesUserInfo(userId, pageable);
    }

    public BookmarkRequest saveBookmark(BookmarkRequest request) {
        Favorite favorite = new Favorite();

        favorite.setTime(request.getTime());
        favorite.setId(new FavoriteId(request.getUserId(), request.getArticleId()));
        favorite.setArticle(articleRepository.findArticleByArticleId(request.getArticleId()));
        favorite.setUser(userRepository.findByUserId(request.getUserId()));

        try {
            Favorite inserted = favoriteRepository.save(favorite);
            if (inserted == null) {
                throw new RuntimeException("Inserted favorite is null");
            }

            request.setArticleId(inserted.getId().getArticleId());
            request.setUserId(inserted.getId().getUserId());
            request.setTime(inserted.getTime());

            return request;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }

    public BookmarkRequest abortBookmark(BookmarkRequest request) {
        Favorite favorite = new Favorite();

        favorite.setTime(request.getTime());
        favorite.setId(new FavoriteId(request.getUserId(), request.getArticleId()));
        favorite.setArticle(articleRepository.findArticleByArticleId(request.getArticleId()));
        favorite.setUser(userRepository.findByUserId(request.getUserId()));

        try {
            favoriteRepository.deleteById(favorite.getId());

            return request;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }

    public BookmarkRequest saveSeeLater(BookmarkRequest request) {
        SeeLater seeLater = new SeeLater();

        seeLater.setTime(request.getTime());
        seeLater.setId(new SeeLaterId(request.getUserId(), request.getArticleId()));
        seeLater.setArticle(articleRepository.findArticleByArticleId(request.getArticleId()));
        seeLater.setUser(userRepository.findByUserId(request.getUserId()));

        try {
            SeeLater inserted = seeLaterRepository.save(seeLater);
            if (inserted == null) {
                throw new RuntimeException("Inserted favorite is null");
            }

            request.setArticleId(inserted.getId().getArticleId());
            request.setUserId(inserted.getId().getUserId());
            request.setTime(inserted.getTime());

            return request;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }

    public BookmarkRequest abortSeeLater(BookmarkRequest request) {
        SeeLater seeLater = new SeeLater();

        seeLater.setTime(request.getTime());
        seeLater.setId(new SeeLaterId(request.getUserId(), request.getArticleId()));
        seeLater.setArticle(articleRepository.findArticleByArticleId(request.getArticleId()));
        seeLater.setUser(userRepository.findByUserId(request.getUserId()));

        try {
            seeLaterRepository.deleteById(seeLater.getId());

            return request;
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage(), DatabaseException.DATA_INTEGRITY_VIOLATION);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage(), AppRuntimeException.UNKNOWN_ERROR);
        }
    }
}
