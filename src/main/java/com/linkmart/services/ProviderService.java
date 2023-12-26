package com.linkmart.services;

import com.linkmart.dtos.OfferDto;
import com.linkmart.dtos.ProviderDetailDto;
import com.linkmart.dtos.ReviewsDto;
import com.linkmart.dtos.VerificationDto;
import com.linkmart.models.Provider;
import com.linkmart.models.ProviderVerification;
import com.linkmart.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProviderVerificationRepository providerVerificationRepository;

    @Autowired
    UserService userService;
    @Autowired
    LocationService locationService;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    OrdersRepository orderRepository;
    @Autowired
    S3Service s3Service;


    public void validateProviderUserId(String userId) {
        var providerByUserId = providerRepository.findProviderByUserId(userId);
        if (providerByUserId == null ) {
            throw new IllegalArgumentException("Invalid UserId ");
        }
    }

    public void validateProviderId(String providerId) {
        var providerByProviderId = providerRepository.findProviderById(providerId);
        if (providerByProviderId == null ) {
            throw new IllegalArgumentException("Invalid ProviderId ");
        }
    }

    public String enrollProvider(String userId, Integer locationId) {
            userService.validateUserId((userId));
            locationService.validateLocationId(locationId);
            Provider provider = new Provider();
            provider.setUserId(userId);
            provider.setLocationId(locationId);
            var providerId = provider.getId();
            providerRepository.saveAndFlush(provider);
            return providerId;
    }

    public String checkIfProvider(String userId) {
        var providerByUserId = providerRepository.getIdByUserId(userId);
        logger.info("providerByUserId: " + providerByUserId);
        return providerByUserId;
    }


    //GET : /api/provider/{providerId}
    public ProviderDetailDto getProviderDetail(String userId) {
        var providerId = providerRepository.getIdByUserId(userId);
        var provider = providerRepository.findProviderById(providerId);
        logger.info("provider: " + provider);
        ProviderDetailDto providerDetailDto = new ProviderDetailDto();
        providerDetailDto.setProviderName(userService.getUserNameById(provider.getUserId()));
        providerDetailDto.setLocationName(locationService.getLocationNameByLocationId(provider.getLocationId()));
        providerDetailDto.setProviderEmail(userService.getUserEmailById(provider.getUserId()));
        providerDetailDto.setNumberOfReviews(provider.getNumberOfReviews());
        providerDetailDto.setStarOfEfficiency(provider.getStarOfEfficiency());
        providerDetailDto.setStarOfAttitude(provider.getStarOfAttitude());
        return  providerDetailDto;
    }


    //POST : /api/provider
    public void providerApplication(String userId, MultipartFile addressDocument, MultipartFile idDocument, MultipartFile bankDocument, Integer locationId) {
        try{
            userService.validateUserId((userId));
            ProviderVerification provider = new ProviderVerification();
            provider.setUserId(userId);
            String addressFile = s3Service.uploadFile(addressDocument);
            String idFile = s3Service.uploadFile(idDocument);
            String bankFile = s3Service.uploadFile(bankDocument);
            provider.setAddressDocument(addressFile);
            provider.setIdDocument(idFile);
            provider.setBankDocument(bankFile);
            //Status: pending
            provider.setStatusId(1);
            var providerVerificationId = providerVerificationRepository.saveAndFlush(provider);
            logger.info("providerVerificationId: " + providerVerificationId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot create provider application");
        }
    }

    //GET : /api/provider
    public VerificationDto getProviderVerificationDetail(String userId) {
        try{
            var verificationDetail = providerVerificationRepository.findProviderVerificationByUserId(userId);
            if (verificationDetail == null) {
                return null;
            } else {
                return verificationDetail;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider verification detail");
        }
    }


    //GET : /api/provider/profile
    public ProviderDetailDto showProviderDetailByUserId(String userId) {
        try{
            logger.info("userId: " + userId);
            Provider providerDetail = providerRepository.findProviderByUserId(userId);
            var providerId = providerDetail.getId();
            logger.info("providerId: " + providerId);
            //Completed and Done
            List<OfferDto> orderDetail = offerRepository.findOfferAndRequestByProviderIdAndStatus(providerId, 8);

            ProviderDetailDto providerDetailDto = new ProviderDetailDto();
            providerDetailDto.setProviderName(userService.getUserNameById(providerDetail.getUserId()));
            providerDetailDto.setStarOfAttitude(providerDetail.getStarOfAttitude());
            providerDetailDto.setStarOfEfficiency(providerDetail.getStarOfEfficiency());
            providerDetailDto.setNumberOfReviews(providerDetail.getNumberOfReviews());
            System.out.println("orderDetail: " + orderDetail.toString());

             List<ReviewsDto> reviewsDtos = new ArrayList<>();
            if (orderDetail == null) {
                providerDetailDto.setReviews(reviewsDtos);
            } else {
                for (OfferDto offer : orderDetail) {
                    logger.info("offer1: " + offer.getOfferId());
                    String orderId = orderRepository.findOrderIdByOfferId(offer.getOfferId());
                    logger.info("orderId: " + orderId);
                    ReviewsDto reviewsDto = new ReviewsDto();
                    reviewsDto.setUsername(offer.getCreatedBy());
                    logger.info("username: " + offer.getCreatedBy());
                    reviewsDto.setPrimaryImage(offer.getPrimaryImage());
                    reviewsDto.setItem(offer.getItem());
                    logger.info("item: " + offer.getItem());
                    reviewsDto.setEfficiency((reviewRepository.findReviewByOrderId(orderId).getReviewEfficiency()));
                    reviewsDto.setAttitude(reviewRepository.findReviewByOrderId(orderId).getReviewAttitude());
                    reviewsDto.setComments(reviewRepository.findReviewByOrderId(orderId).getReviewRemark());
                    reviewsDtos.add(reviewsDto);
                }
            }
            providerDetailDto.setReviews(reviewsDtos);
            return providerDetailDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider detail");
        }
    }

    //Get: /provider/profile/{providerId}
    public ProviderDetailDto publicShowProviderDetailByUserId(String providerId) {
        try{
            logger.info("providerId1: " + providerId);
            Provider providerDetail = providerRepository.findProviderById(providerId);
            logger.info("providerId2: " + providerId);
            //Completed and Done
            List<OfferDto> orderDetail = offerRepository.findOfferAndRequestByProviderIdAndStatus(providerId, 8);

            ProviderDetailDto providerDetailDto = new ProviderDetailDto();
            providerDetailDto.setProviderName(userService.getUserNameById(providerDetail.getUserId()));
            providerDetailDto.setStarOfAttitude(providerDetail.getStarOfAttitude());
            providerDetailDto.setStarOfEfficiency(providerDetail.getStarOfEfficiency());
            providerDetailDto.setNumberOfReviews(providerDetail.getNumberOfReviews());
            System.out.println("orderDetail: " + orderDetail.toString());

            List<ReviewsDto> reviewsDtos = new ArrayList<>();
            if (orderDetail == null) {
                providerDetailDto.setReviews(reviewsDtos);
            } else {
                for (OfferDto offer : orderDetail) {
                    logger.info("offer1: " + offer.getOfferId());
                    String orderId = orderRepository.findOrderIdByOfferId(offer.getOfferId());
                    logger.info("orderId: " + orderId);
                    ReviewsDto reviewsDto = new ReviewsDto();
                    reviewsDto.setUsername(offer.getCreatedBy());
                    logger.info("username: " + offer.getCreatedBy());
                    reviewsDto.setPrimaryImage(offer.getPrimaryImage());
                    reviewsDto.setItem(offer.getItem());
                    logger.info("item: " + offer.getItem());
                    reviewsDto.setEfficiency((reviewRepository.findReviewByOrderId(orderId).getReviewEfficiency()));
                    reviewsDto.setAttitude(reviewRepository.findReviewByOrderId(orderId).getReviewAttitude());
                    reviewsDto.setComments(reviewRepository.findReviewByOrderId(orderId).getReviewRemark());
                    reviewsDtos.add(reviewsDto);
                }
            }
            providerDetailDto.setReviews(reviewsDtos);
            return providerDetailDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider detail");
        }
    }
}
