package com.linkmart.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int locationId;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="location", cascade={CascadeType.ALL})
    private List<RequestModel> requestModel;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "location", cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Provider> providerList;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public List<RequestModel> getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(List<RequestModel> requestModel) {
        this.requestModel = requestModel;
    }
}
