package com.linkmart.dtos;


import com.linkmart.models.ImageModel;
import com.linkmart.models.RequestModel;

import java.util.List;

public interface RequestDto {
    String getRequestId();
    String getCreatedBy();
    String getLocationName();
    String getPrimaryImage();
    int getOfferPrice();
    String getItem();
    String getCreatedAt();
    String getUpdatedAt();
}
