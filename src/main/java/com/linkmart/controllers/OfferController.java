package com.linkmart.controllers;

import com.linkmart.dtos.GetOneOfferDto;
import com.linkmart.dtos.OfferDto;
import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.models.Offer;
import com.linkmart.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/api/offer/request/{requestId}")
    public List<GetOneOfferDto> getOfferByRequestId (
            @PathVariable(value = "requestId") String requestId) throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getOfferByRequestId(userId, requestId);
        } catch (Exception e) {
            throw new Exception("Cannot get offer in controller");
        }
    }

    @GetMapping(value = "/api/offer/myOffer")
    public List<OfferDto> getMyOffer () throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getMyOffer(userId);
        } catch (Exception e) {
            throw new Exception("Cannot get offer in controller");
        }
    }

    @PutMapping(value = "/api/offer/{offerId}")
    public RequestResponseWithMessageDto updateOffer (
            @PathVariable(value = "offerId", required = false) String offerId,
            @RequestParam(value = "price", required = false) Integer price,
            @RequestParam(value = "estimatedProcessTime", required = false) Integer estimatedProcessTime,
            @RequestParam(value = "offerRemark", required = false) String offerRemark) throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            offerService.updateOffer(userId, offerId, price, estimatedProcessTime, offerRemark);
            return new RequestResponseWithMessageDto("Offer updated successfully");
        } catch (Exception e) {
            throw new Exception("Cannot update offer in controller");
        }
    }
}
