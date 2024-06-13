package com.server.vnnews.dto;

public class HistoryArticleDTO {
    private Long articleId;
    private String title;
    private String description;
    private byte[] thumbnail;

    public HistoryArticleDTO(Long articleId, String title, String description, byte[] thumbnail) {
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
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
}

