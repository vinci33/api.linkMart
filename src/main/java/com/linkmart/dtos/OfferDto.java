package com.linkmart.dtos;

public interface OfferDto {
    String getOfferId();
    String getRequestId();
    String getCreatedBy();
    String getItem();
    String getPrimaryImage();
    int getPrice();
    int getEstimatedProcessTime();
    String getOfferStatus();
}
