package com.server.vnnews.dto;

import java.util.Date;

public class ArticleInNewsFeedRequestDTO {
    // article
    private Long articleId;
    private String title;
    private String description;
    private String thumbnail;
    private String thumbnailName;
    private Date createTime;
    private Date modifyTime;
    private Long viewCount;
    private Long commentCount;
    // author
    private Long userId;
    private String userName;
    private String avatar;
    private Long followCount;
    private Long saveCount;

    public Long getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Long saveCount) {
        this.saveCount = saveCount;
    }
    // Construction
    public ArticleInNewsFeedRequestDTO() {
    }

    public ArticleInNewsFeedRequestDTO(Long articleId, String title, String description, String thumbnail, String thumbnailName, Date createTime, Date modifyTime, Long viewCount, Long commentCount, Long userId, String userName, String avatar, Long followCount, Long saveCount) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.thumbnailName = thumbnailName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.followCount = followCount;
        this.saveCount = saveCount;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }
}
