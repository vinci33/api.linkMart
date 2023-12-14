package com.linkmart.dtos;

import com.linkmart.models.ImageModel;

import java.util.List;

public interface OneRequestDto {
    String getRequestId();
    int getLocationId();
    String getLocationName();
    int getCategoryId();
    String getCategoryName();
    String getItemDetail();
    String getItem();
    String getPrimaryImage();
    List<ImageModel> getImages();
    String getUrl();
    int getQuantity();
    String getRequestRemark();
    int getOfferPrice();
    String getCreatedBy();
    String getCreatedAt();
    String getUpdatedAt();
}
