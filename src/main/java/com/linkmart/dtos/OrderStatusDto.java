package com.linkmart.dtos;

public class OrderStatusDto {
    private int id;
    private String orderStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderStatusDto{" +
                "id=" + id +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
