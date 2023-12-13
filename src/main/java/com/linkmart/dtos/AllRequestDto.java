package com.linkmart.dtos;

import com.linkmart.models.ImageModel;

import java.util.List;

public class AllRequestDto {
    private String RequestId;
    private String created_by;
    private int location_id;
    private int category_id;
    private String item;
    private String url;
    private int quantity;
    private int offer_price;
    private String request_remark;
    private String createdAt;
    private String updatedAt;
    private List<ImageModel> images;
}
