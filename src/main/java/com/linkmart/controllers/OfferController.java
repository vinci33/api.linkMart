package com.linkmart.controllers;

import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.models.Offer;
import com.linkmart.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OfferService offerService;

    @Autowired
    HttpServletRequest request;

    @PostMapping(value = "/api/offer")
    public RequestResponseWithMessageDto postOffer (
            @RequestParam(value = "requestId") String requestId,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "estimatedProcessTime") Integer estimatedProcessTime,
            @RequestParam(value = "offerRemark", required = false) String offerRemark) throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            offerService.postOffer(userId, requestId, price, estimatedProcessTime, offerRemark);
            return new RequestResponseWithMessageDto("Offer created successfully");
        } catch (Exception e) {
            throw new Exception("Cannot create offer in controller");
        }
    }
}
