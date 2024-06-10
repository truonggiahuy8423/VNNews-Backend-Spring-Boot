package com.server.vnnews.dto;

import java.util.Date;

public class CommentPostingRequest {
    private String content;

    private Date createTime;

    private Date modifyTime;

    private Long articleId;

    private Long parentCommentId;

    private Long userId;

    public CommentPostingRequest() {
    }

    public CommentPostingRequest(String content, Date createTime, Date modifyTime, Long articleId, Long parentCommentId, Long userId) {
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.articleId = articleId;
        this.parentCommentId = parentCommentId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
