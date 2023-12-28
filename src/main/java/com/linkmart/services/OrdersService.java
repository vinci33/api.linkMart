package com.linkmart.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkmart.dtos.*;
import com.linkmart.mappers.OrdersMapper;
import com.linkmart.models.*;
import com.linkmart.repositories.*;
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
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final OfferRepository offerRepository;
    private final OrdersRepository ordersRepository;
    private final OfferService offerService;
    private final ReviewService reviewService;
    private final UserAddressRepository userAddressRepository;
    private final ProviderRepository providerRepository;
    private final S3Service s3Service;
    private final ApplicationEventPublisher eventPublisher;

    public OrdersService(RequestRepository requestRepository,RequestService requestService, OfferRepository offerRepository, OrdersRepository ordersRepository, OfferService offerService, ReviewService reviewService, LocationRepository locationRepository, UserAddressRepository userAddressRepository, ProviderRepository providerRepository, S3Service s3Service, ApplicationEventPublisher eventPublisher) {
        this.requestService = requestService;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.ordersRepository = ordersRepository;
        this.offerService = offerService;
        this.reviewService = reviewService;
        this.userAddressRepository = userAddressRepository;
        this.providerRepository = providerRepository;
        this.s3Service = s3Service;
        this.eventPublisher = eventPublisher;
    }



    @Transactional
    public String createOrder(Boolean success, String userId, String offerId, Integer userAddressId) {
        //find accepted offer
        var offer = offerRepository.findOfferByOfferId(offerId);
        if (offer == null) {
            throw new IllegalArgumentException("OfferId not found");
        }
        var requestId = offer.getRequestId();
        var allOpenOffers = offerService.getOfferByRequestIdAndOfferStatusId(requestId, 1);
        allOpenOffers.forEach(offer::setStatusAborted);
        offer.setOfferStatusId(8);
        offerRepository.saveAndFlush(offer);
        requestService.updateRequestStatus(requestId,
                true , false);
        Orders order = new Orders();
        order.setOfferId(offerId);
        order.setUserAddressId(userAddressId);
        order.setOrderStatusId(2);
        ordersRepository.saveAndFlush(order);
        return order.getId();
    }



    public List<OrdersDto> getOrdersByUserIdAndStatus(String userId, List<String> orderStatuses){
        return  ordersRepository.findOrdersByUserIdAndStatus(userId, orderStatuses);

    }

    public List<OrdersDto> userGetOrdersByUserIdAndStatusFromUser(String userId, List<String> orderStatuses){
        return  ordersRepository.findOrdersByUserIdAndStatusFromUser(userId, orderStatuses);
    }


    public void updateOrderShippingOrderId(String orderId, Integer logisticCompanyId, String shippingOrderNo, MultipartFile file) {
        Orders order = (Orders)ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.setOrderStatusId(3);
        order.setLogisticCompanyId(logisticCompanyId);
        order.setShippingOrderNo(shippingOrderNo);
        String orderProof = s3Service.uploadShipmentFile(file);
        order.setShipmentProof(orderProof);
//        updateOrderStatus();
        ordersRepository.saveAndFlush(order);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void updateOrderStatus() {
        List<Orders> shippedOrders = ordersRepository.findOrdersByOrderStatusId(3);
        for (Orders order : shippedOrders) {
            Timestamp timestamp = order.getUpdatedAt();
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            if (dateTime.plusMinutes(5).isBefore(LocalDateTime.now())) {
                order.setOrderStatusId(4);
                ordersRepository.save(order);
                eventPublisher.publishEvent(order.getId());
            }
        }
    }

    public OrdersByOrderIdDto getOrdersDetailByOrderId (String orderId){
        OrdersByOrderIdWithoutImageDto orderDetail = ordersRepository.findOrdersDetailByOrderId(orderId);
        if (orderDetail == null) {
            throw new IllegalArgumentException("Order not found 2");
        }
        List<String> images = ordersRepository.findImagesByOrderId(orderId);
        OrdersByOrderIdDto orders =  OrdersMapper.INSTANCE.toOrdersByOrderIdDto(orderDetail);
        orders.setImages(images);
        String itemDetailJson = ordersRepository.findItemDetailByOrderId(orderId);
        Gson gson = new Gson();
        ItemDetailModel itemDetailMap = gson.fromJson(itemDetailJson, ItemDetailModel.class);
        orders.setItemDetail(itemDetailMap);
        String Address = userAddressRepository.findAddressById(orderDetail.getUserAddressId());
        orders.setAddress(Address);
        return orders;
    }

    public void reviewOrder(String orderId, String userId, Float efficiency, Float attitude, String reviewRemark) {
        var order = ordersRepository.getOneByOfferId(orderId);
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
        review.setOrderId(orderId);
        review.setProviderId(offer.getProviderId());
        review.setReviewEfficiency(efficiency);
        review.setReviewAttitude(attitude);
        review.setReviewRemark(reviewRemark);
        order.setOrderStatusId(6);
        ordersRepository.saveAndFlush(order);
        reviewService.saveReview(review);
        Float averageEfficiency = reviewService.getAverageEfficiency(offer.getProviderId());
        Float averageAttitude = reviewService.getAverageAttitude(offer.getProviderId());
        String providerId = offer.getProviderId();
        //review count
        Integer reviewCount = reviewService.getReviewCount(providerId);
        providerRepository.updateProviderRating(providerId, averageEfficiency, averageAttitude, reviewCount);
    }

    public void updateOrderReceived(String orderId, String userId) {
        var order = ordersRepository.getOneByOfferId(orderId);
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
        }
        if (!request.getCreatedBy().equals(userId)) {
            throw new IllegalArgumentException("User not authorized");
        }
        order.setOrderStatusId(4);
        ordersRepository.saveAndFlush(order);
    }
}
