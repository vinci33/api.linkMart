package com.linkmart.dtos;



import java.sql.Timestamp;

public interface OrdersDto {
    String getOrderId();
   String getOrderStatus();
   Integer getProviderId();
     String getProviderName();
     String getItem();
     String getPrimaryImage();
     String getQuantity();
     float getPrice();
     Integer getEstimatedProcessTime();
     Timestamp getCreatedAt();


}
