package com.linkmart.dtos;

public class ProviderDashboardDto {

    private Float averageEfficiency;
    private Float averageAttitude;
    private Integer reviewCount;
    private Float balance;
    private Integer offerCount;
    private Integer taskCount;

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

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
}
