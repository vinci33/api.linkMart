package com.linkmart.dtos;

import com.linkmart.models.Request;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

public class RequestContent {
    private String id;
    private String created_by;
    private int location_id;
    private int category_id;
    private String item;
    private String image;
    private String url;
    private int quantity;
    private int offer_price;
    private String request_remark;
    private String createdAt;
    private String updatedAt;
    private List<Request> requests;


}
