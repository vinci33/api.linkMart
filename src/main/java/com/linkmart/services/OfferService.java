package com.linkmart.services;

import com.linkmart.dtos.*;
import com.linkmart.dtos.GetOneOfferDto;
import com.linkmart.dtos.OfferDto;
import com.linkmart.forms.AcceptOfferForm;
import com.linkmart.models.Offer;
import com.linkmart.models.Provider;
import com.linkmart.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfferService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserAddressService userAddressService;

    @Autowired
    ProviderService providerService;

    @Autowired
    UserPaymentMethodRepository userPaymentMethodRepository;

    //POST route: /api/offer
    @Transactional
    public void postOffer(String userId, String requestId,
                           Integer price, Integer estimatedProcessTime,
                           String offerRemark)
            throws Exception {
        try {
            var providerId = providerRepository.getIdByUserId(userId);
            logger.info("providerId: " + providerId);
//             check if provider exists
            if (providerId == null) {
                throw new Exception("Provider not found");
            }
            var thisRequest = requestRepository.findCreatedByByRequestId(requestId);
            logger.info("thisRequest: " + thisRequest);
            // check if request exists and not created by this provider
            if (thisRequest == null) {
                throw new RuntimeException("Request not found");
            } else if (thisRequest.equals(userId)) {
                throw new RuntimeException("Cannot offer to your own request");
            }
            Offer offer = new Offer();
            offer.makeOfferCase();
            offer.setProviderId(providerId);
            offer.setOfferStatusId(6);
            offer.setRequestId(requestId);
            offer.setPrice(price);
            offer.setEstimatedProcessTime(estimatedProcessTime);
            offer.setOfferRemark(offerRemark);
            requestRepository.updateRequestStatusIdByRequestId(requestId);
            offerRepository.saveAndFlush(offer);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //GET route: /api/offer/request/{requestId}
    @Transactional
    public List<GetOneOfferDto> getOfferByRequestId(String userId, String requestId) throws Exception {
        try {
//            var providerId = providerRepository.getIdByUserId(userId);
//            logger.info("providerId: " + providerId);
//            // check if provider exists
//            if (providerId == null) {
//                throw new Exception("Provider not found");
//            }
//            var thisRequest = requestRepository.findCreatedByByRequestId(requestId);
//            logger.info("thisRequest: " + requestId);
            // check if request exists and not created by this provider
            var request = requestRepository.findRequestModelByRequestId(requestId);
            if (request == null) {
                throw new Exception("Request not found");
            }
            Offer offer = offerRepository.findByRequestId(requestId);
            ArrayList <GetOneOfferDto> offerList = new ArrayList<>();
            if (offer == null) {
                return offerList;
            }
            Provider provider = providerRepository.findProviderById(offer.getProviderId());
            String userName = userRepository.findByUserId(provider.getUserId());
            String status = statusRepository.findStatusName(offer.getOfferStatusId());
            List<GetOneOfferDto> getManyOfferDto = new ArrayList<>();
            GetOneOfferDto getOneOfferDto = new GetOneOfferDto();
            getOneOfferDto.setOfferId(offer.getOfferId());
            getOneOfferDto.setRequestId(offer.getRequestId());
            getOneOfferDto.setProviderId(offer.getProviderId());
            getOneOfferDto.setProviderName(userName);
            getOneOfferDto.setEfficiency(provider.getStarOfAttitude());
            getOneOfferDto.setAttitude(provider.getStarOfEfficiency());
            getOneOfferDto.setStatusName(status);
            getOneOfferDto.setPrice(offer.getPrice());
            getOneOfferDto.setEstimatedProcessTime(offer.getEstimatedProcessTime());
            getOneOfferDto.setOfferRemark(offer.getOfferRemark());
            getManyOfferDto.add(getOneOfferDto);
            return getManyOfferDto;
        } catch (Exception e) {
            throw new Exception("Cannot getOfferByRequestId");
        }
    }

    //GET route : /api/offer/myOffer
    @Transactional
    public List<OfferDto> getMyOffer(String userId) throws Exception {
        try {
            var providerId = providerRepository.getIdByUserId(userId);
            logger.info("providerId: " + providerId);
            // check if provider exists
            if (providerId == null) {
                throw new Exception("Provider not found");
            }
            List<OfferDto> offer = offerRepository.findActiveByRequestId(providerId);
            ArrayList <OfferDto> offerList = new ArrayList<>();
            if (offer == null) {
                return offerList;
            }
            return offer;
        } catch (Exception e) {
            throw new Exception("Cannot get offer in database");
        }
    }

    //PUT route : /api/offer/{offerId}
    public void updateOffer(String userId, String offerId,
                            Integer price, Integer estimatedProcessTime,
                            String offerRemark)
            throws Exception {
        try {
            var providerId = providerRepository.getIdByUserId(userId);
            logger.info("providerId: " + providerId);
            // check if provider exists
            if (providerId == null) {
                throw new Exception("Provider not found");
            }
            var thisOffer = offerRepository.findOfferById(offerId);
            //check if offer exists and not created by this provider
            if (thisOffer == null) {
                throw new Exception("Offer not found");
            } else if (!thisOffer.getProviderId().equals(providerId)) {
                throw new Exception("Cannot update offer that not created by you");
            }
            if (price != null) {
                thisOffer.setPrice(price);
            }
            if (estimatedProcessTime != null) {
                thisOffer.setEstimatedProcessTime(estimatedProcessTime);
            }
            if (offerRemark != null) {
                thisOffer.setOfferRemark(offerRemark);
            }
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            thisOffer.setUpdatedAt(currentTimestamp);
            offerRepository.save(thisOffer);
        } catch (Exception e) {
            throw new Exception("Cannot update offer in database");
        }
    }

    public void validateOfferId(String offerId) {
        var validOfferId = offerRepository.findOfferByOfferId(offerId);
        if (validOfferId == null ) {
            throw new IllegalArgumentException("Invalid offerId ");
        }
    }

    public PaymentDetailDto acceptOffer(String userId, String offerId,Integer addressId) {
        try {
            validateOfferId(offerId);
            logger.info("userId: " + userId);
            userService.validateUserId(userId);
            userAddressService.validateUserAddressId(addressId);
            logger.info("offerId: " + offerId);
            var offer = offerRepository.findOfferByOfferId(offerId);
            var request = requestRepository.findRequestModelByRequestId(offer.getRequestId());
            UserDetailDto userDetail = userService.getUserDetailById(userId);
            logger.info("userDetail: " + userDetail);
            var username = userDetail.getUsername();
            var userEmail = userDetail.getUserEmail();
            var userPaymentMethod = userDetail.getUserPaymentMethod().get(0);
            var userAddress = userDetail.getUserAddress().get(0);
            var price = offer.getPrice();
            var providerId  =  offer.getProviderId();
            ProviderDetailDto providerDetail = providerService.getProviderDetail(providerId);
            var providerName = providerDetail.getProviderName();
            var providerLocation = providerDetail.getLocationName();
            var item = request.getItem();
            var quantity = request.getQuantity();
            var primaryImage = request.getPrimaryImage();
            PaymentDetailDto paymentDetailDto = new PaymentDetailDto();
            paymentDetailDto.setOfferId(offer.getOfferId());
            paymentDetailDto.setUserUsername(username);
            paymentDetailDto.setUserEmail(userEmail);
            paymentDetailDto.setUserAddress(userAddress);
            paymentDetailDto.setUserPaymentMethod(userPaymentMethod);
            paymentDetailDto.setProviderUsername(providerName);
            paymentDetailDto.setLocation(providerLocation);
            paymentDetailDto.setItem(item);
            paymentDetailDto.setQuantity(quantity);
            paymentDetailDto.setPrimary_image(primaryImage);
            paymentDetailDto.setPrice(price);
            return paymentDetailDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ID: " + e.getMessage(), e);
        }
    }

    public List<Offer> getOfferByRequestIdAndOfferStatusId(String requestId, Integer offerStatusId){
        return offerRepository.findOfferByRequestIdAndOfferStatusId(requestId, offerStatusId);
    }
    public void setStatusPending(Offer offer){
        offer.setOfferStatusId(1);
    }
    public String getStatusPending(){
        return "Pending";
    }

    public void setStatusInprogress(Offer offer){
        offer.setOfferStatusId(2);
    }
    public String getStatusInprogress(){
        return "In Progress";
    }
    public void setStatusAborted(Offer offer){
        offer.setOfferStatusId(3);
    }
    public String getStatusAborted(){
        return "Aborted";
    }
    public void setStatusRejected(Offer offer){
        offer.setOfferStatusId(4);
    }
    public String getStatusRejected(){
        return "Rejected";
    }
    public void setStatusCompleted(Offer offer){
        offer.setOfferStatusId(5);
    }
    public String getStatusCompleted(){
        return "Completed";
    }
    public void setStatusOpen(Offer offer){
        offer.setOfferStatusId(6);
    }
    public String getStatusOpen(){
        return "Open";
    }
    public void setStatusClosed(Offer offer){
        offer.setOfferStatusId(7);
    }
    public String getStatusClosed(){
        return "Closed";
    }
}
