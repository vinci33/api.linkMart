package com.linkmart.dtos;

public class ProviderDashboardDto {

    private Float averageEfficiency;
    private Float averageAttitude;
    private Integer reviewCount;
    private Float balance;
    private Integer offerCount;
    private Integer activeTaskCount;
    private Integer completedTaskCount;

    public Float getAverageEfficiency() {
        return averageEfficiency;
    }

    public void setAverageEfficiency(Float averageEfficiency) {
        this.averageEfficiency = averageEfficiency;
    }

    public Float getAverageAttitude() {
        return averageAttitude;
    }

    public void setAverageAttitude(Float averageAttitude) {
        this.averageAttitude = averageAttitude;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(Integer offerCount) {
        this.offerCount = offerCount;
    }

    public Integer getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(Integer completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public Integer getActiveTaskCount() {
        return activeTaskCount;
    }

    public void setActiveTaskCount(Integer activeTaskCount) {
        this.activeTaskCount = activeTaskCount;
    }
}
