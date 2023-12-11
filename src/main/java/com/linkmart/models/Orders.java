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
}
