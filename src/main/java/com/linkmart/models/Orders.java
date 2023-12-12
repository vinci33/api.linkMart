package com.linkmart.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private int id;

    @Column(name = "order_id")
    private String offerId;

    @Column(name = "logistic_company_id")
    private int logisticCompanyId;

    @Column(name = "shipping_order_no")
    private int shippingOrderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="logistic_company_id", updatable = false, insertable = false)
    private LogisticCompany logisticCompany;

    @OneToMany(mappedBy = "orders")
    private List<ReportCase> reportCaseList;

    //TODO: Add relation to ReportCase table

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public int getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(int logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public int getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(int shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", offerId='" + offerId + '\'' +
                ", logisticCompanyId=" + logisticCompanyId +
                ", shippingOrderNo=" + shippingOrderNo +
//                ", logisticCompany=" + logisticCompany +
                ", reportCaseList=" + reportCaseList +
                '}';
    }
}
