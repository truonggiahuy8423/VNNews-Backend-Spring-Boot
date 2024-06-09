package com.server.vnnews.dto;

import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.entity.Category;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;
public class ArticleScrollPageDTO{
    private Long articleId;
    private String title;
    private String description;
    private byte[] thumbnail;
    private String thumbnailName;
    private Date createTime;
    private Date modifyTime;
    private Long viewCount;
    private Long commentCount;
    private Long favoriteCount;
    private Long userId;
    private String userName;
    private byte[] avatar;
    private Long followCount;
    private List<BodyItemDTO> bodyItemList;
    // Hàm khởi tạo sử dụng NewsFeedArticleDTO

    public ArticleScrollPageDTO(Long articleId, String title, String description, byte[] thumbnail, String thumbnailName, Date createTime, Date modifyTime, Long viewCount, Long commentCount, Long favoriteCount, Long userId, String userName, byte[] avatar, Long followCount) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.thumbnailName = thumbnailName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.favoriteCount = favoriteCount;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.followCount = followCount;
    }

    public ArticleScrollPageDTO(Long articleId, String title, String description, byte[] thumbnail, String thumbnailName, Date createTime, Date modifyTime, Long viewCount, Long commentCount, Long favoriteCount, Long userId, String userName, byte[] avatar, Long followCount, List<BodyItemDTO> bodyItemList) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.thumbnailName = thumbnailName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.favoriteCount = favoriteCount;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.followCount = followCount;
        this.bodyItemList = bodyItemList;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }

    public List<BodyItemDTO> getBodyItemList() {
        return bodyItemList;
    }

    public void setBodyItemList(List<BodyItemDTO> bodyItemList) {
        this.bodyItemList = bodyItemList;
    }

}