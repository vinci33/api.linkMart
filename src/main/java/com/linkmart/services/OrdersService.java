package com.linkmart.services;

import com.linkmart.dtos.OrdersDto;
import com.linkmart.models.Orders;
import com.linkmart.repositories.OfferRepository;
import com.linkmart.repositories.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        var offer = offerRepository.findOfferByOfferId(offerId);
        if (offer == null) {
            throw new IllegalArgumentException("OfferId not found");
        }
        var requestId = offer.getRequestId();
        var allOpenOffers = offerService.getOfferByRequestIdAndOfferStatusId(requestId, 6);
        allOpenOffers.forEach(offerService::setStatusAborted);
        offerService.setStatusPending(offer);
        logger.info("Offer status: " + offer.getOfferStatusId());
        offerRepository.saveAndFlush(offer);
        logger.info("All open offers: " +allOpenOffers );
        requestService.updateRequestStatus(requestId,
                true , false);
//        offer.setOfferStatusId(1);
        Orders order = new Orders();
        order.setOfferId(offerId);
        order.setUserAddressId(userAddressId);
        order.setLogisticCompanyId(1);
        setStatusCreate(order);
        logger.info("Order status: " + order.getOrderStatusId());
        ordersRepository.saveAndFlush(order);
        return order.getId();
    }

//    public List<OrdersDto> getOrdersByUserId(String userId) {
//        var orders = ordersRepository.findOrdersByUserId(userId);
//        return orderDtos;
//    }
}
