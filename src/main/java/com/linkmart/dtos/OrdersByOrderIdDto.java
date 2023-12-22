package com.linkmart.dtos;


import com.fasterxml.jackson.databind.JsonNode;
import com.linkmart.models.ItemDetailModel;
import io.swagger.v3.core.util.Json;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrdersByOrderIdDto {
    private String orderId;
    private String orderStatus;
    private String providerId;
    private String providerName;
    private String item ;
    private String primaryImage;
    private String quantity;
    private Float price;
    private Integer estimatedProcessTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String requestId;
    private String locationName;
    private String createdBy;
    private List<String> images;
    private ItemDetailModel itemDetail;
    private String url;
    private String requestRemark;
    private String shippingOrderNo;
    private String logisticCompanyName;
    private String Address;
    private String logisticCompanyUrl;

    public String getLogisticCompanyUrl() {
        return logisticCompanyUrl;
    }

    public void setLogisticCompanyUrl(String logisticCompanyUrl) {
        this.logisticCompanyUrl = logisticCompanyUrl;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getLogisticCompanyName() {
        return logisticCompanyName;
    }

    public void setLogisticCompanyName(String logisticCompanyName) {
        this.logisticCompanyName = logisticCompanyName;
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

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
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
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public void setEstimatedProcessTime(Integer estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ItemDetailModel getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetailModel itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestRemark() {
        return requestRemark;
    }

    public void setRequestRemark(String requestRemark) {
        this.requestRemark = requestRemark;
    }
}
