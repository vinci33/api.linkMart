package com.linkmart.services;

import com.linkmart.dtos.*;
import com.linkmart.dtos.GetOneOfferDto;
import com.linkmart.dtos.OfferDto;
import com.linkmart.forms.AcceptOfferForm;
import com.linkmart.models.Offer;
import com.linkmart.models.Provider;
import com.linkmart.repositories.*;
import org.antlr.v4.runtime.ListTokenSource;
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
    LocationService locationService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserPaymentMethodRepository userPaymentMethodRepository;

    @Transactional
    public Integer getOfferPriceByOfferId(String offerId) {
        try {
            var offer = offerRepository.findOfferByOfferId(offerId);
            if (offer == null) {
                throw new IllegalArgumentException("Offer not found");
            }
            return offer.getPrice();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ID: " + e.getMessage(), e);
        }
    }

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
            //status: pending
            offer.setOfferStatusId(1);
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
            // check if request exists and not created by this provider
            logger.info("ServiceRequestId: " + requestId);
            String createdBy = requestRepository.findCreatedByByRequestId(requestId);
            logger.info("requests: " + createdBy);
            ArrayList <GetOneOfferDto> offerList = new ArrayList<>();
            //check if offer is exist
            var offers  = offerRepository.findByRequestId(requestId);
                if (offers == null) {
                    return offerList;
                }
            for (Offer offer : offers) {
                Provider provider = providerRepository.findProviderById(offer.getProviderId());
                String userName = userRepository.findByUserId(provider.getUserId());
                String status = statusRepository.findStatusName(offer.getOfferStatusId());
                GetOneOfferDto getOneOfferDto = new GetOneOfferDto();
                getOneOfferDto.setOfferId(offer.getOfferId());
                getOneOfferDto.setRequestId(offer.getRequestId());
                getOneOfferDto.setProviderId(offer.getProviderId());
                getOneOfferDto.setProviderName(userName);
                getOneOfferDto.setEfficiency(provider.getStarOfEfficiency());
                getOneOfferDto.setAttitude(provider.getStarOfAttitude());
                getOneOfferDto.setStatusName(status);
                getOneOfferDto.setPrice(offer.getPrice());
                getOneOfferDto.setEstimatedProcessTime(offer.getEstimatedProcessTime());
                getOneOfferDto.setOfferRemark(offer.getOfferRemark());
                getOneOfferDto.setReviewCount(provider.getNumberOfReviews());
                offerList.add(getOneOfferDto);
            }
            return offerList;
        } catch (Exception e) {
            throw new Exception("Cannot get offer by requestId");
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
            List<OfferDto> offer = offerRepository.findActiveByProviderId(providerId);
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
            Offer thisOffer = offerRepository.findOfferById(offerId);
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
            logger.info("OfferId: " + thisOffer.getOfferId());
            logger.info("Status Id:"  + thisOffer.getOfferStatusId());
            logger.info("Provider Id: " + thisOffer.getProviderId());
            logger.info("Offer Id: " + thisOffer.getRequestId());
            logger.info("Price Id: " + thisOffer.getPrice());
            logger.info("Process Time: " + thisOffer.getEstimatedProcessTime());
            logger.info("Request remark: " + thisOffer.getOfferRemark());
            offerRepository.saveAndFlush(thisOffer);
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

    public void deleteOffer(String userId, String offerId) {
        try {
            validateOfferId(offerId);
            logger.info("userId: " + userId);
            userService.validateUserId(userId);
            logger.info("offerId: " + offerId);
            var offer = offerRepository.findOfferByOfferId(offerId);
            var providerId = offer.getProviderId();
            var provider = providerRepository.findProviderById(providerId);
            if (!providerId.equals(provider.getId())) {
                throw new IllegalArgumentException("Invalid userId");
            }
            //change to aborted status
            offer.setOfferId(offerId);
            offer.setOfferStatusId(3);
            offerRepository.save(offer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ID: " + e.getMessage(), e);
        }
    }

    public void requestRejectedByUser (String userId, String offerId) {
        try {
            validateOfferId(offerId);
            logger.info("userId: " + userId);
            userService.validateUserId(userId);
            logger.info("offerId: " + offerId);
            var offer = offerRepository.findOfferByOfferId(offerId);
            var createdBy = requestRepository.findCreatedByByRequestId(offer.getRequestId());
            if (!createdBy.equals(userId)) {
                throw new IllegalArgumentException("Invalid userId");
            }
            //change to rejected status
            logger.info("offerId 2: " + offerId);
            offer.setOfferId(offerId);
            offer.setOfferStatusId(4);
            offerRepository.save(offer);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new IllegalArgumentException("Error : " + e.getMessage(), e);
        }
    }

    public OfferDetailDto getOfferByOfferId (String userId, String offerId) {
        try {
            validateOfferId(offerId);
            userService.validateUserId(userId);
            var offer = offerRepository.findOfferByOfferId(offerId);
            if (offer == null) {
                throw new IllegalArgumentException("No Offer found");
            }
            if (!offer.getProviderId().equals(userId)) {
                throw new IllegalArgumentException("this offer is not created by this user");
            }
            //find request
            var requestId = offer.getRequestId();
            var request = requestRepository.findRequestModelByRequestId(requestId);
            //find who create the request (userID)
            String createdBy = request.getCreatedBy();
            var user = userService.getUserNameById(createdBy);
            OfferDetailDto offerDetailDto = new OfferDetailDto();
            offerDetailDto.setRequestId(offer.getRequestId());
            offerDetailDto.setLocationId(request.getLocationId());
            offerDetailDto.setLocationName(locationService.getLocationNameByLocationId(request.getLocationId()));
            offerDetailDto.setCategoryId(request.getCategoryId());
            offerDetailDto.setCategoryName(categoryRepository.findCategoryNameByCategoryId(request.getCategoryId()));
            offerDetailDto.setPrimaryImage(request.getPrimaryImage());
            offerDetailDto.setItem(request.getItem());
            offerDetailDto.setItem(request.getItem());
            offerDetailDto.setItemDetail(request.getItemDetail());
            offerDetailDto.setUrl(request.getUrl());
            offerDetailDto.setQuantity(request.getQuantity());
            offerDetailDto.setRequestRemark(request.getRequestRemark());
            offerDetailDto.setOfferPrice(request.getOfferPrice());
            offerDetailDto.setCreatedBy(user);
            offerDetailDto.setCreatedAt(request.getCreatedAt());
            offerDetailDto.setUpdatedAt(request.getUpdatedAt());
            offerDetailDto.setImages(request.getImages());
            offerDetailDto.setOfferStatus(statusRepository.findStatusName(offer.getOfferStatusId()));
            offerDetailDto.setEstimatedProcessTime(offer.getEstimatedProcessTime());
            offerDetailDto.setPrice(offer.getPrice());
            offerDetailDto.setOfferRemark(offer.getOfferRemark());
            return offerDetailDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ID: " + e.getMessage(), e);
        }
    }

    //ToDo
    public OfferCheckDto checkIfHasOffer(String requestId, String userId) {
        try {
            logger.info("requestId: " + requestId);
            logger.info("userId: " + userId);
            var providerId = providerRepository.getIdByUserId(userId);
            var offer = offerRepository.findByProviderIdAndRequestId(providerId, requestId);
            logger.info("offer: " + offer);
            if (offer == null) {
                return null;
            }
            OfferCheckDto offerCheckDto = new OfferCheckDto();
            offerCheckDto.setOfferId(offer.getOfferId());
            offerCheckDto.setEstimatedProcessTime(offer.getEstimatedProcessTime());
            offerCheckDto.setPrice(offer.getPrice());
            offerCheckDto.setOfferRemark(offer.getOfferRemark());
            return offerCheckDto;
            } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
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
