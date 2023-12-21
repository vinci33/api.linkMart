package com.linkmart.controllers;

import com.linkmart.dtos.*;
import com.linkmart.forms.AcceptOfferForm;
import com.linkmart.forms.OfferForm;
import com.linkmart.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class OfferController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OfferService offerService;

    @Autowired
    HttpServletRequest request;

    @PostMapping(value = "/api/offer")
    public RequestResponseWithMessageDto postOffer (@RequestBody OfferForm offerForm)
            throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            var requestId = offerForm.getRequestId();
            if (requestId == null) {
                throw new Exception("Request id is null");
            }
            var price = offerForm.getPrice();
            if (price == null) {
                throw new Exception("Price is null");
            }
            var estimatedProcessTime = offerForm.getEstimatedProcessTime();
            if (estimatedProcessTime == null) {
                throw new Exception("Estimated process time is null");
            }
            var offerRemark = offerForm.getOfferRemark();
            if (offerRemark == null) {
                throw new Exception("Offer remark is null");
            }
            offerService.postOffer(userId, requestId, price, estimatedProcessTime, offerRemark);
            return new RequestResponseWithMessageDto("Offer created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //for requester
    @GetMapping(value = "/api/offer/request/{requestId}")
    public List<GetOneOfferDto> getOfferByRequestId (
            @PathVariable(value = "requestId") String requestId) throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getOfferByRequestId(userId, requestId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //for provider
    @GetMapping(value = "/api/offer/myOffer")
    public List<OfferDto> getMyOffer () throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getMyOffer(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot get offer in controller");
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

    @GetMapping(value = "/api/offer/paymentInfo/{offerId}/{addressId}")
    public PaymentDetailDto acceptOffer (
            @PathVariable("offerId") String offerId,
            @PathVariable("addressId") Integer addressId,
            HttpServletRequest request) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            PaymentDetailDto paymentDetail = offerService.acceptOffer(userId, offerId, addressId);
            return paymentDetail;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/api/offer/{offerId}")
    public PaymentDto redirectOffer (
            @RequestBody AcceptOfferForm acceptOfferForm,
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            Integer addressId = acceptOfferForm.getUserAddressId();
            Integer price = offerService.getOfferPriceByOfferId(offerId);
//            String redirectUrl = "https://api.fight2gether.com/api/offer/paymentInfo/" + offerId + "/" + addressId;
            String redirectUrl = "/user/payment/" + offerId +"?addressId=" + addressId + "&price=" + price;
            return new PaymentDto(redirectUrl, offerId, addressId, price);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //6.1.3 get offer detail
    @GetMapping(value = "/api/offer/{offerId}")
    public OfferDetailDto getOneOffer (
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            OfferDetailDto offer = offerService.getOfferByOfferId(userId, offerId);
            return offer;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/api/offer/{offerId}")
    public void deleteOffer (
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            offerService.deleteOffer(userId, offerId);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
