package com.linkmart.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


}
