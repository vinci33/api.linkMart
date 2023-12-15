package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    private String id; //ULID

    @Column(name = "user_id")
    private String userId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", updatable = false, insertable = false)
    private Location location;

    @OneToMany(mappedBy="provider" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    List<Offer> offerList;

    @OneToMany(mappedBy="provider" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<ProviderVerification> providerVerificationList;

    public Provider() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", locationId=" + locationId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
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

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
}
