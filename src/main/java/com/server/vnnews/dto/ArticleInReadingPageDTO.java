package com.server.vnnews.dto;

import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.entity.Category;

import java.util.List;

public class ArticleInReadingPageDTO extends NewsFeedArticleDTO {
    private List<BodyItem> bodyItemList;
    private List<Category> categories;
    private int isSaved;
    private int isSeeLater;

    public int getIsSaved() {
        return isSaved;
    }

    public void getIsSaved(int saved) {
        isSaved = saved;
    }

    public int getIsSeeLater() {
        return isSeeLater;
    }

    public void setSeeLater(int seeLater) {
        isSeeLater = seeLater;
    }

    public List<BodyItem> getBodyItemList() {
        return bodyItemList;
    }

    // Hàm khởi tạo sử dụng NewsFeedArticleDTO

    public ArticleInReadingPageDTO(NewsFeedArticleDTO newsFeedArticleDTO, List<BodyItem> bodyItemList, List<Category> categories, int isSaved, int isSeeLater) {
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
        this.isSaved = isSaved;
        this.isSeeLater = isSeeLater;
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
