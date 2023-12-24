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

    public ProviderDetailDto getProviderDetail(String providerId) {
        validateProviderId(providerId);
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

    public VerificationDto getProviderVerificationDetail(String userId) {
        try{
            var verificationDetail = providerVerificationRepository.findProviderVerificationByUserId(userId);
            logger.info("verificationDetail: " + verificationDetail);
            return verificationDetail;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider verification detail");
        }
    }

    //TODO: get provider detail by userId
//    public ProviderDetailDto getProviderDetailByUserId(String userId) {
//        try{
//            var providerDetail = providerRepository.findProviderByUserId(userId);
//            logger.info("providerDetail: " + providerDetail);
//            var providerId = providerDetail.getId();
//            logger.info("providerId: " + providerId);
//            //Completed and Done
//            List<OfferDto> orderDetail = offerRepository.findOfferAndRequestByProviderIdAndStatus(providerId, 8);
//
//            ProviderDetailDto providerDetailDto = new ProviderDetailDto();
//            providerDetailDto.setProviderName(userService.getUserNameById(providerDetail.getUserId()));
//            providerDetailDto.setStarOfAttitude(providerDetail.getStarOfAttitude());
//            providerDetailDto.setStarOfEfficiency(providerDetail.getStarOfEfficiency());
//            providerDetailDto.setNumberOfReviews(providerDetail.getNumberOfReviews());
//
//            List<ReviewsDto> reviews  = new ArrayList<>();
//            for (OfferDto offer : orderDetail) {
//                ReviewsDto reviewsDto = new ReviewsDto();
//                reviewsDto.setUsername(offer.getCreatedBy());
//                reviewsDto.setPrimaryImage(offer.getPrimaryImage());
//                reviewsDto.setItem(offer.getItem());
//                reviewsDto.setEfficiency((reviewRepository.findReviewByOrderId(offer.getOfferId())).getReviewEfficiency());
//                reviewsDto.setAttitude(reviewRepository.findReviewByOrderId(offer.getOfferId()).getReviewAttitude());
//                reviewsDto.setComments(reviewRepository.findReviewByOrderId(offer.getOfferId()).getReviewRemark());
//                reviews.add(reviewsDto);
//            }
//            providerDetailDto.setReviews(reviews);
//            return providerDetailDto;
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Cannot get provider detail");
//        }
//    }
}
