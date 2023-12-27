package com.linkmart.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "provider")
public class Provider {

    @Id
    private String id; //ULID

    @Column(name = "user_id")
    private String userId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "provider_verification_id")
    private Integer providerVerificationId;

    @Column(name = "number_of_reviews")
    private Integer numberOfReviews;

    @Column(name = "star_of_efficiency")
    private Float starOfEfficiency;

    @Column(name = "star_of_attitude")
    private Float starOfAttitude;

    @Column(name = "bio")
    private String biography;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_verification_id", updatable = false, insertable = false)
    private ProviderVerification providerVerification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", updatable = false, insertable = false)
    private Location location;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy="provider")
    List<Offer> offerList;

    @OneToMany(mappedBy="provider" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<ProviderVerification> providerVerificationList;

    @OneToMany(mappedBy="provider" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Review> reviewList;

    public Provider() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public Integer getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Integer numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
