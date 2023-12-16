package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @Column(name = "id")
    private String offerId;

    @Column(name = "request_id")
    private String requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_id", updatable = false, insertable = false)
    private RequestModel requestModel;

    @Column(name = "provider_id")
    private String providerId;

    @ManyToOne
    @JoinColumn(name="provider_id", updatable = false, insertable = false)
    private Provider provider;

    @Column(name = "offer_status_id")
    private int offerStatusId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="offer_status_id", updatable = false, insertable = false)
    private Status status;

    @Column(name = "estimated_process_time")
    private int estimatedProcessTime;

    @Column(name = "price")
    private int price;

    @Column(name = "offer_remark")
    private String offerRemark;

    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private String updatedAt;


    public void makeOfferCase() {
        ULID ulid = new ULID();
        this.offerId = ulid.nextULID();
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

    public int getOfferStatusId() {
        return offerStatusId;
    }

    public void setOfferStatusId(int offerStatusId) {
        this.offerStatusId = offerStatusId;
    }

    public int getEstimatedProcessTime() {
        return estimatedProcessTime;
    }

    public void setEstimatedProcessTime(int estimatedProcessTime) {
        this.estimatedProcessTime = estimatedProcessTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOfferRemark() {
        return offerRemark;
    }

    public void setOfferRemark(String offerRemark) {
        this.offerRemark = offerRemark;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
