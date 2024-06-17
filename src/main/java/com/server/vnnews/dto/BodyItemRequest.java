package com.server.vnnews.dto;

public class BodyItemRequest {
    private Long bodyItemId;

    private String imageName;

    private String dataImage;

    private String content;

    private Integer ordinalNumber;

    private String bodyTitle;

    private String articleTitle;
    public static final  int HEADER = 0;
    public static final  int IMAGE_ITEM = 1;
    public static final  int BODY_TITLE_ITEM = 2;
    public static final  int PARAGRAPH_ITEM = 3;

    private int itemType;
    // Constructors, getters, and setters


    public BodyItemRequest(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public BodyItemRequest() {

    }
    public BodyItemRequest(Long bodyItemId, String imageName,String dataImage, String content, Integer ordinalNumber, String bodyTitle) {
        this.bodyItemId = bodyItemId;
        this.imageName = imageName;
        this.dataImage = dataImage;
        this.content = content;
        this.ordinalNumber = ordinalNumber;
        this.bodyTitle = bodyTitle;
    }
    public BodyItemRequest(Long bodyItemId, String imageName, String dataImage, String content, Integer ordinalNumber, String bodyTitle, String articleTitle) {
        this.bodyItemId = bodyItemId;
        this.imageName = imageName;
        this.dataImage = dataImage;
        this.content = content;
        this.ordinalNumber = ordinalNumber;
        this.bodyTitle = bodyTitle;
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

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

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
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
    @Override
    public String toString() {
        return "{" +
                "\"bodyItemId\":" + bodyItemId +
                ", \"imageName\":\"" + imageName + '\"' +
                ", \"dataImage\":\"" + dataImage + "\"" +  // Always an empty string
                ", \"content\":\"" + content + '\"' +
                ", \"ordinalNumber\":" + ordinalNumber +
                ", \"bodyTitle\":\"" + bodyTitle + '\"' +
                ", \"articleTitle\":\"" + articleTitle + '\"' +
                ", \"itemType\":" + itemType +
                '}';
    }

}
