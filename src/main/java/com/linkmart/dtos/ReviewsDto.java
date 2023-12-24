package com.linkmart.dtos;

public class ReviewsDto {
    private String username;
    private String primaryImage;
    private String item;
    private Float efficiency;
    private Float attitude;
    private String comments;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Float efficiency) {
        this.efficiency = efficiency;
    }

    public Float getAttitude() {
        return attitude;
    }

    public void setAttitude(Float attitude) {
        this.attitude = attitude;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }
}
