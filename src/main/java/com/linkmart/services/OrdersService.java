package com.linkmart.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkmart.dtos.*;
import com.linkmart.mappers.OrdersMapper;
import com.linkmart.models.*;
import com.linkmart.repositories.LocationRepository;
import com.linkmart.repositories.OfferRepository;
import com.linkmart.repositories.OrdersRepository;
import com.linkmart.repositories.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestService requestService;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OfferService offerService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    S3Service s3Service;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

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
        order.setOrderStatusId(2);
        setStatusCreate(order);
        logger.info("Order status: " + order.getOrderStatusId());
        ordersRepository.saveAndFlush(order);
        return order.getId();
    }

//    public List<OrdersByOrderIdAndStatusDto> getOrdersByUserId(String userId) {
//        return ordersRepository.findOrdersByUserId(userId);
//
//    }

    public List<OrdersDto> getOrdersByUserIdAndStatus(String userId, List<String> orderStatuses){
        return  ordersRepository.findOrdersByUserIdAndStatus(userId, orderStatuses);

    }

    public List<OrdersDto> userGetOrdersByUserIdAndStatusFromUser(String userId, List<String> orderStatuses){
        return  ordersRepository.findOrdersByUserIdAndStatusFromUser(userId, orderStatuses);

    }

    public void updateOrderShippingOrderId( String orderId, Integer logisticCompanyId, String shippingOrderNo, MultipartFile file) {
        Orders order = (Orders)ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.setOrderStatusId(3);
        order.setLogisticCompanyId(logisticCompanyId);
        order.setShippingOrderNo(shippingOrderNo);
        String orderProof = s3Service.uploadFile(file);
        order.setShipmentProof(orderProof);
        ordersRepository.saveAndFlush(order);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void updateOrderStatus() {
        List<Orders> shippedOrders = ordersRepository.findOrdersByOrderStatusId(3);
        for (Orders order : shippedOrders) {
            Timestamp timestamp = order.getUpdatedAt();
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            if (dateTime.plusMinutes(1).isBefore(LocalDateTime.now())) {
                order.setOrderStatusId(4);
                ordersRepository.save(order);
                eventPublisher.publishEvent(order.getId());
            }
        }
    }

    public OrdersByOrderIdDto getOrdersDetailByOrderId (String orderId){
        logger.info("Get order detail: " + orderId);
        OrdersByOrderIdWithoutImageDto orderDetail = ordersRepository.findOrdersDetailByOrderId(orderId);
        if (orderDetail == null) {
            throw new IllegalArgumentException("Order not found");
        }
        List<String> images = ordersRepository.findImagesByOrderId(orderId);
        OrdersByOrderIdDto orders =  OrdersMapper.INSTANCE.toOrdersByOrderIdDto(orderDetail);
        orders.setImages(images);
        String itemDetailJson = ordersRepository.findItemDetailByOrderId(orderId);
        Gson gson = new Gson();
        ItemDetailModel itemDetailMap = gson.fromJson(itemDetailJson, ItemDetailModel.class);
        orders.setItemDetail(itemDetailMap);
        String Address = locationRepository.findByLocationId(orderDetail.getUserAddressId());
        orders.setAddress(Address);
        return orders;
    }

    public void reviewOrder(String orderId, String userId, Float efficiency, Float attitude, String reviewRemark) {
        logger.info("Review order: " + orderId);
        var order = ordersRepository.getOneByOfferId(orderId);
        logger.info("After SQL Review Order: " + order);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        Offer offer = offerRepository.findOfferByOfferId(order.getOfferId());
        if (offer == null) {
            throw new IllegalArgumentException("Offer not found");
        }
        RequestModel request = requestRepository.getRequestByRequestId(offer.getRequestId());
        if (request == null) {
            throw new IllegalArgumentException("Request not found");
        } else if (!request.getCreatedBy().equals(userId)) {
            throw new IllegalArgumentException("User not authorized");
        }
        Review review = new Review();
        review.setOrderID(orderId);
        review.setProviderId(offer.getProviderId());
        review.setReviewEfficiency(efficiency);
        review.setReviewAttitude(attitude);
        review.setReviewRemark(reviewRemark);
//        review suppose is finish the order and got the product already,
//        that's y is suitable to set status to completed
//        order.setOrderStatusId(4);
//        ordersRepository.saveAndFlush(order);
        reviewService.saveReview(review);


    }
}
