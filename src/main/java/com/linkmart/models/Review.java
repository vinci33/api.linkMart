package com.linkmart.models;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column (name = "id")
    private int reviewId;

    @Column(name = "orders_id")
    private String orderID; //ULID

    @Column(name = "provider_id")
    private String providerId; //ULID

    @Column(name = "review_efficiency")
    private Integer reviewEfficiency;

    @Column(name = "review_attitude")
    private Integer reviewAttitude;

    @Column(name = "review_remark")
    private String reviewRemark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", updatable = false, insertable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", updatable = false, insertable = false)
    private Provider provider;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", orderID='" + orderID + '\'' +
                ", providerId='" + providerId + '\'' +
                ", reviewEfficiency=" + reviewEfficiency +
                ", reviewAttitude=" + reviewAttitude +
                ", reviewRemark='" + reviewRemark + '\'' +
                ", orders=" + orders +
                ", provider=" + provider +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }



    public Integer getReviewEfficiency() {
        return reviewEfficiency;
    }

    public void setReviewEfficiency(Integer reviewEfficiency) {
        this.reviewEfficiency = reviewEfficiency;
    }

    public Integer getReviewAttitude() {
        return reviewAttitude;
    }

    public void setReviewAttitude(Integer reviewAttitude) {
        this.reviewAttitude = reviewAttitude;
    }

    public String getReviewRemark() {
        return reviewRemark;
    }

    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }



    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId && Objects.equals(orderID, review.orderID) && Objects.equals(providerId, review.providerId) && Objects.equals(reviewEfficiency, review.reviewEfficiency) && Objects.equals(reviewAttitude, review.reviewAttitude) && Objects.equals(reviewRemark, review.reviewRemark) && Objects.equals(orders, review.orders) && Objects.equals(provider, review.provider) && Objects.equals(createdAt, review.createdAt) && Objects.equals(updatedAt, review.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, orderID, providerId, reviewEfficiency, reviewAttitude, reviewRemark, orders, provider, createdAt, updatedAt);
    }
}
