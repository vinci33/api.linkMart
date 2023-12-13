package com.linkmart.dtos;


public interface RequestDto {
    String getRequestId();
    String getCreatedBy();
    String getLocationName();
    String getItem();
    String getPrimaryImage();
    int getOfferPrice();
    String getCreatedAt();
    String getUpdatedAt();
}
