package com.linkmart.dtos;

import com.linkmart.models.Location;

import java.util.List;

public class ProviderDetailDto {
    private String providerName;
    private String locationName;
    private String providerEmail;
    private Integer numberOfReviews;
    private Float starOfEfficiency;
    private Float starOfAttitude;
    private List<ReviewsDto> reviews;

    private String biography;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProviderName() {
        return providerName;
    }

    public Integer getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Integer numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public Float getStarOfEfficiency() {
        return starOfEfficiency;
    }

    public void setStarOfEfficiency(Float starOfEfficiency) {
        this.starOfEfficiency = starOfEfficiency;
    }

    public Float getStarOfAttitude() {
        return starOfAttitude;
    }

    public void setStarOfAttitude(Float starOfAttitude) {
        this.starOfAttitude = starOfAttitude;
    }

    public void setReviews(List<ReviewsDto> reviews) {
        this.reviews = reviews;
    }

    public List<ReviewsDto> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ProviderDetailDto{" +
                "providerName='" + providerName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", providerEmail='" + providerEmail + '\'' +
                ", numberOfReviews=" + numberOfReviews +
                ", starOfEfficiency=" + starOfEfficiency +
                ", starOfAttitude=" + starOfAttitude +
                '}';
    }
}
