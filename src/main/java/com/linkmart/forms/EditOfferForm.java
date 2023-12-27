package com.linkmart.forms;

public class EditOfferForm {
    private Integer price;
    private Integer estimatedProcessTime;
    private String offerRemark;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public void setEstimatedProcessTime(Integer estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public String getOfferRemark() {
        return offerRemark;
    }

    public void setOfferRemark(String offerRemark) {
        this.offerRemark = offerRemark;
    }
}
