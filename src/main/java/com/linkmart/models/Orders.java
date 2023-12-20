package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private String id;

    @Column(name = "offer_id")
    private String offerId;

    @Column(name = "user_address_id")
    private Integer userAddressId;

    @Column(name = "logistic_company_id")
    private Integer logisticCompanyId;

    @Column(name = "order_status_id")
    private Integer orderStatusId;

    @Column(name = "shipping_order_no")
    private String shippingOrderNo;

    public Integer getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(Integer logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    @OneToMany(mappedBy = "orders")
    private List<ReportCase> reportCaseList;

    //TODO: Add relation to ReportCase table

    public Orders() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Integer getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }




    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }
}
