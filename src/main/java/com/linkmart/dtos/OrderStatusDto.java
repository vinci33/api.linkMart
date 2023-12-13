package com.linkmart.dtos;

public class OrderStatusDto {
    private int OrderStatusId;
    private String orderStatus;

    public int getOrderStatusId() {
        return OrderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        OrderStatusId = orderStatusId;
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
                "id=" + OrderStatusId +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
