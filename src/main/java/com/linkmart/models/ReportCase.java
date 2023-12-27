package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "report_case")
public class ReportCase {

    @Id
    @Column(name = "id")
    private String reportCaseId; //ULID

    @Column(name = "orders_id")
    private String ordersId; //ULID

    @Column(name = "admins_id")
    private String adminsId; //ULID

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "report_subject")
    private String reportSubject;

    @Column(name = "report_description")
    private String reportDescription;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", updatable = false, insertable = false)
    private Orders orders;
    //TODO : add relation from orders table to ReportCase table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admins_id", updatable = false, insertable = false)
    private Admins admins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", updatable = false, insertable = false)
    private Status status;


    public void MakeReportCase() {
        ULID ulid = new ULID();
        this.reportCaseId = ulid.nextULID();
    }

    public String getReportCaseId() {
        return reportCaseId;
    }

    public void setReportCaseId(String reportCaseId) {
        this.reportCaseId = reportCaseId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(String adminsId) {
        this.adminsId = adminsId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
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

    public String getReportSubject() {
        return reportSubject;
    }

    public void setReportSubject(String reportSubject) {
        this.reportSubject = reportSubject;
    }

    @Override
    public String toString() {
        return "ReportCase{" +
                "reportCaseId='" + reportCaseId + '\'' +
                ", ordersId='" + ordersId + '\'' +
                ", adminsId='" + adminsId + '\'' +
                ", statusId=" + statusId +
                ", reportDescription='" + reportDescription + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
