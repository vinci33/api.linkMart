package com.linkmart.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkmart.dtos.*;
import com.linkmart.mappers.OrdersMapper;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.Orders;
import com.linkmart.repositories.OfferRepository;
import com.linkmart.repositories.OrdersRepository;
import io.swagger.v3.core.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestService requestService;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OfferService offerService;

    public OrdersService(RequestService requestService, OfferRepository offerRepository, OrdersRepository ordersRepository, OfferService offerService) {
        this.requestService = requestService;
        this.offerRepository = offerRepository;
        this.ordersRepository = ordersRepository;
        this.offerService = offerService;
    }

    public void setStatusCreate(Orders order) {
        order.setOrderStatusId(1);
    }
    public String getStatusCreate(Orders order) {
        return "Created";
    }
    public void setStatusInProgress(Orders order) {
        order.setOrderStatusId(2);
    }
    public String getStatusInProgress(Orders order) {
        return "In Progress";
    }
    public void setStatusShipped(Orders order) {
        order.setOrderStatusId(3);
    }
    public String getStatusShipped(Orders order) {
        return "Shipped";
    }
    public void setStatusCompleted(Orders order) {
        order.setOrderStatusId(4);
    }
    public String getStatusCompleted(Orders order) {
        return "Completed";
    }

    @Transactional
    public String createOrder(Boolean success, String userId, String offerId, Integer userAddressId) {
        //find accepted offer
        var offer = offerRepository.findOfferByOfferId(offerId);
        if (offer == null) {
            throw new IllegalArgumentException("OfferId not found");
        }
        var requestId = offer.getRequestId();
        var allOpenOffers = offerService.getOfferByRequestIdAndOfferStatusId(requestId, 6);
        //all Offer status aborted
        allOpenOffers.forEach(offerService::setStatusAborted);
        offerService.setStatusPending(offer);
        logger.info("Offer status: " + offer.getOfferStatusId());
        offerRepository.saveAndFlush(offer);
        logger.info("All open offers: " + allOpenOffers );
        requestService.updateRequestStatus(requestId,
                true , false);
        Orders order = new Orders();
        order.setOfferId(offerId);
        order.setUserAddressId(userAddressId);
        setStatusCreate(order);
        logger.info("Order status: " + order.getOrderStatusId());
        ordersRepository.saveAndFlush(order);
        return order.getId();
    }

    public List<OrdersDtoWithDays> getOrdersByUserId(String userId) {
        List<OrdersDto> orders = ordersRepository.findOrdersByUserId(userId);
        return orders.stream()
                .map(order -> {
                    OrdersDtoWithDays orderWithDays = new OrdersDtoWithDays();
                    orderWithDays.setOrderId(order.getOrderId());
                    orderWithDays.setOrderStatus(order.getOrderStatus());
                    orderWithDays.setProviderId(order.getProviderId());
                    orderWithDays.setProviderName(order.getProviderName());
                    orderWithDays.setItem(order.getItem());
                    orderWithDays.setPrimaryImage(order.getPrimaryImage());
                    orderWithDays.setQuantity(order.getQuantity());
                    orderWithDays.setPrice(order.getPrice());
                    orderWithDays.setEstimatedProcessTime(order.getEstimatedProcessTime() + "(days)");  // Add "days" suffix
                    orderWithDays.setCreatedAt(order.getCreatedAt());
                    return orderWithDays;
                })
                .collect(Collectors.toList());
    }

    public List<OrdersDtoWithDays> getOrdersByUserIdAndStatus(String userId, List<String> orderStatuses){
        List<OrdersDto> orders = ordersRepository.findOrdersByUserIdAndStatus(userId, orderStatuses);
        return orders.stream()
                .map(order -> {
                    OrdersDtoWithDays orderWithDays = new OrdersDtoWithDays();
                    orderWithDays.setOrderId(order.getOrderId());
                    orderWithDays.setOrderStatus(order.getOrderStatus());
                    orderWithDays.setProviderId(order.getProviderId());
                    orderWithDays.setProviderName(order.getProviderName());
                    orderWithDays.setItem(order.getItem());
                    orderWithDays.setPrimaryImage(order.getPrimaryImage());
                    orderWithDays.setQuantity(order.getQuantity());
                    orderWithDays.setPrice(order.getPrice());
                    orderWithDays.setEstimatedProcessTime(order.getEstimatedProcessTime() + " (days)");  // Add "days" suffix
                    orderWithDays.setCreatedAt(order.getCreatedAt());
                    return orderWithDays;
                })
                .collect(Collectors.toList());
    }

    public void updateOrderShippingOrderId( String orderId, Integer logisticCompanyId, String shippingOrderNo) {
        Orders order = (Orders)ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.setOrderStatusId(3);
        order.setLogisticCompanyId(logisticCompanyId);
        order.setShippingOrderNo(shippingOrderNo);
//        setStatusShipped(order);

        ordersRepository.saveAndFlush(order);
    }

    public OrdersByOrderIdDto getOrdersDetailByOrderId (String orderId){
        OrdersByOrderIdWithoutImageDto orderDetail = ordersRepository.findOrdersDetailByOrderId(orderId);
        if (orderDetail == null) {
            throw new IllegalArgumentException("Order not found");
        }
        List<String> images = ordersRepository.findImagesByOrderId(orderId);
        OrdersByOrderIdDto orders =  OrdersMapper.INSTANCE.toOrdersByOrderIdDto(orderDetail);
        orders.setImages(images);
        String itemDetailJson = ordersRepository.findItemDetailByOrderId(orderId);
        Gson gson = new Gson();
        Map<String, Object> itemDetailMap = gson.fromJson(itemDetailJson, new TypeToken<Map<String, Object>>(){}.getType());
        orders.setItemDetail(itemDetailMap);
        return orders;
    }
}
