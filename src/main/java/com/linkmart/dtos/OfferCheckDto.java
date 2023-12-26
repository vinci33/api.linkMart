package com.linkmart.dtos;

public class OfferCheckDto {
    private String offerId;
    private Integer estimatedProcessTime;
    private Integer price;
    private String offerRemark;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Integer getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public void setEstimatedProcessTime(Integer estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOfferRemark() {
        return offerRemark;
    }

    public void setOfferRemark(String offerRemark) {
        this.offerRemark = offerRemark;
    }
}
