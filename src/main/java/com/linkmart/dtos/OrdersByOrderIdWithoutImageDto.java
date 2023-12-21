package com.linkmart.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.core.util.Json;

import java.sql.Timestamp;

public interface OrdersByOrderIdWithoutImageDto {
    String getOrderId();
    String getOrderStatus();
    String getProviderId();
    String getProviderName();
    String getItem();
    String getQuantity();
    String getPrimaryImage();
    Float getPrice();
    Integer getEstimatedProcessTime();
    Timestamp getCreatedAt();
    Timestamp getUpdatedAt();
    String getRequestId();
    String getLocationName();
    String getCreatedBy();
//    JsonNode getItemDetail();
    String getUrl();
    String getRequestRemark();
    String getShipmentProof();
    String getShippingOrderNo();
    String getLogisticCompanyName();


}
