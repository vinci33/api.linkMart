package com.linkmart.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class OrdersDto {
    private String orderId;
    private String orderStatus;
    private Integer providerId;
    private String providerName;
    private String item;
    private String primaryImage;
    private String Quantity;
    private float price;
    private Integer estimatedProcessTime;
    private Timestamp createdAt;

    @JsonProperty("estimatedProcessTime")
    public String getEstimatedProcessTimeInDays() {
        return estimatedProcessTime + " days";
    }

    public void setEstimatedProcessTime(Integer estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public Integer getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
