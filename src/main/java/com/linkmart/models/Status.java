package com.linkmart.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int StatusId;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "status", cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<ProviderVerification> providerVerificationList;

    @OneToMany(mappedBy = "status", cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<ReportCase> reportCaseList;

    @OneToMany(mappedBy = "status", cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Offer> offerList;
    //TODO: Add relationship with other tables as one-to-many>provider_verification, offer, report_case
    @Override
    public String toString() {
        return "Status{" +
                "id=" + StatusId +
                ", statusName='" + statusName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
}
