package com.linkmart.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "provider_verification")
public class ProviderVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private String userId; //ULID

    @Column(name = "provider_id")
    private String providerId; //ULID

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "id_document")
    private String idDocument;

    @Column(name = "address_document")
    private String addressDocument;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", updatable = false, insertable = false)
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", updatable = false, insertable = false)
    private Status status;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ProviderVerification{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", statusId=" + statusId +
                ", idDocument='" + idDocument + '\'' +
                ", addressDocument='" + addressDocument + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public String getAddressDocument() {
        return addressDocument;
    }

    public void setAddressDocument(String addressDocument) {
        this.addressDocument = addressDocument;
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
