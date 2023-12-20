package com.linkmart.dtos;

public class GetOneOfferDto {
    private String offerId;
    private String requestId;
    private String providerId;
    private String providerName;
    private Float efficiency;
    private Float attitude;
    private String statusName;
    private int price;
    private int estimatedProcessTime;
    private String offerRemark;
    private Integer reviewCount;

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public void setEstimatedProcessTime(int estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public String getOfferRemark() {
        return offerRemark;
    }

    public void setOfferRemark(String offerRemark) {
        this.offerRemark = offerRemark;
    }
}
