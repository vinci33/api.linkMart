package com.linkmart.models;

import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "logistic_company")
public class LogisticCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int logisticCompanyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "logisticCompany",
            cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Orders> orders;

    public int getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(int logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
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
    public String toString() {
        return "LogisticCompany{" +
                "logisticCompanyId=" + logisticCompanyId +
                ", companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", orders=" + orders +
                '}';
    }
}
