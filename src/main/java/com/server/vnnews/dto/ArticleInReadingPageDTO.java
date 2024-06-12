package com.server.vnnews.dto;

import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.entity.Category;

import java.util.List;

public class ArticleInReadingPageDTO extends NewsFeedArticleDTO {
    private List<BodyItem> bodyItemList;
    private List<Category> categories;


    public List<BodyItem> getBodyItemList() {
        return bodyItemList;
    }

    // Hàm khởi tạo sử dụng NewsFeedArticleDTO
    public ArticleInReadingPageDTO(NewsFeedArticleDTO newsFeedArticleDTO, List<BodyItem> bodyItemList, List<Category> categories) {
        super(newsFeedArticleDTO.getArticleId(),
                newsFeedArticleDTO.getTitle(),
                newsFeedArticleDTO.getDescription(),
                newsFeedArticleDTO.getThumbnail(),
                newsFeedArticleDTO.getThumbnailName(),
                newsFeedArticleDTO.getCreateTime(),
                newsFeedArticleDTO.getModifyTime(),
                newsFeedArticleDTO.getViewCount(),
                newsFeedArticleDTO.getCommentCount(),
                newsFeedArticleDTO.getUserId(),
                newsFeedArticleDTO.getUserName(),
                newsFeedArticleDTO.getAvatar(),
                newsFeedArticleDTO.getFollowCount(),
                newsFeedArticleDTO.getSaveCount()
        );
        this.bodyItemList = bodyItemList;
        this.categories = categories;
    }

    public void setBodyItemList(List<BodyItem> bodyItemList) {
        this.bodyItemList = bodyItemList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
