package com.linkmart.dtos;

import com.linkmart.models.CategoryModel;
import com.linkmart.models.Location;
import com.linkmart.models.User;

import java.sql.Timestamp;

public class AnotherRequestDto {
    private String requestId;
    private String categoryName;
    private String locationName;
    private String createdBy;
    private String item;
    private String primaryImage;
    private int offerPrice;
    private String createdAt;
    private String updatedAt;
    private CategoryModel category;
    private Location location;
    private User user;

    //Get categoryName
    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getCategoryName() {
        return this.category.getCategoryName();
    }
    //Get categoryName

    //Get locationName
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationName() {
        return this.location.getLocationName();
    }
    //Get locationName


    //Get Username
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    //Get Username


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
