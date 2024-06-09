package com.server.vnnews.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "body_item")
public class BodyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_item_id", columnDefinition = "BIGINT")
    private Long bodyItemId;

    @Column(name = "image_name",columnDefinition = "TEXT")
    private String imageName;

    @Lob
    @Column(name = "data_image")
    private byte[] dataImage;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "ordinal_number")
    private Integer ordinalNumber;

    @Column(name = "body_title", columnDefinition = "TEXT")
    private String bodyTitle;

    // Relation "One"

    // Relation "Many"
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Constructors, getters, and setters

    public Long getBodyItemId() {
        return bodyItemId;
    }

    public void setBodyItemId(Long bodyItemId) {
        this.bodyItemId = bodyItemId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getDataImage() {
        return dataImage;
    }

    public void setDataImage(byte[] dataImage) {
        this.dataImage = dataImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getBodyTitle() {
        return bodyTitle;
    }

    public void setBodyTitle(String bodyTitle) {
        this.bodyTitle = bodyTitle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
