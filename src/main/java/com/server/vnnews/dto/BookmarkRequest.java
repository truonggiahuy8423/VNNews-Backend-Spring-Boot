package com.server.vnnews.dto;

import java.util.Date;

public class BookmarkRequest {
    private Long userId;
    private Long articleId;
    private Date time;

    public BookmarkRequest(Long userId, Long articleId, Date time) {
        this.userId = userId;
        this.articleId = articleId;
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
