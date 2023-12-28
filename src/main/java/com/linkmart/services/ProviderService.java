package com.linkmart.services;

import com.linkmart.dtos.*;
import com.linkmart.models.Provider;
import com.linkmart.models.ProviderVerification;
import com.linkmart.models.Review;
import com.linkmart.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
        return providerByUserId;
    }


    //GET : /api/provider/{providerId}
    public ProviderDetailDto getProviderDetail(String userId) {
        var providerId = providerRepository.getIdByUserId(userId);
        var provider = providerRepository.findProviderById(providerId);
        ProviderDetailDto providerDetailDto = new ProviderDetailDto();
        providerDetailDto.setProviderName(userService.getUserNameById(provider.getUserId()));
        providerDetailDto.setLocationName(locationService.getLocationNameByLocationId(provider.getLocationId()));
        providerDetailDto.setProviderEmail(userService.getUserEmailById(provider.getUserId()));
        providerDetailDto.setNumberOfReviews(provider.getNumberOfReviews());
        providerDetailDto.setStarOfEfficiency(provider.getStarOfEfficiency());
        providerDetailDto.setStarOfAttitude(provider.getStarOfAttitude());
        return  providerDetailDto;
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
            logger.info("This is provider: " + providerId);
            //Completed and Done
            List<OfferDto> orderDetail = offerRepository.findOfferAndRequestByProviderIdAndStatus(providerId, 8);

            ProviderDetailDto providerDetailDto = new ProviderDetailDto();
            providerDetailDto.setProviderName(userService.getUserNameById(providerDetail.getUserId()));
            providerDetailDto.setLocationName(locationService.getLocationNameByLocationId(providerDetail.getLocationId()));
            providerDetailDto.setStarOfAttitude(providerDetail.getStarOfAttitude());
            providerDetailDto.setStarOfEfficiency(providerDetail.getStarOfEfficiency());
            if (providerDetail.getNumberOfReviews() == null) {
                providerDetailDto.setNumberOfReviews(0);
            } else {
                providerDetailDto.setNumberOfReviews(providerDetail.getNumberOfReviews());
            }
            if (providerDetail.getBiography() == null) {
                providerDetailDto.setBiography(null);
            } else {
                providerDetailDto.setBiography(providerDetail.getBiography());
            }

             List<ReviewsDto> reviewsDtos = new ArrayList<>();
             try{
                 if (orderDetail == null) {
                     providerDetailDto.setReviews(reviewsDtos);
                 } else {
                     for (OfferDto offer : orderDetail) {
                         ReviewsDto reviewsDto = new ReviewsDto();
                         String orderId = orderRepository.findOrderIdByOfferId(offer.getOfferId());
                         Review review = reviewRepository.findReviewByOrderId(orderId);
                         if (review == null) {
                             logger.info("review is null");
                             continue;
                         }
                         reviewsDto.setUsername(offer.getCreatedBy());
                         reviewsDto.setPrimaryImage(offer.getPrimaryImage());
                         reviewsDto.setItem(offer.getItem());
                         reviewsDto.setEfficiency((reviewRepository.findReviewByOrderId(orderId).getReviewEfficiency()));
                         reviewsDto.setAttitude(reviewRepository.findReviewByOrderId(orderId).getReviewAttitude());
                         reviewsDto.setComments(reviewRepository.findReviewByOrderId(orderId).getReviewRemark());
                         reviewsDtos.add(reviewsDto);
                     }
                 }
                 providerDetailDto.setReviews(reviewsDtos);
                 return providerDetailDto;
             } finally {
                 providerDetailDto.setReviews(reviewsDtos);
             }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider detail");
        }
    }

    //Get: /provider/profile/{providerId}
    public ProviderDetailDto publicShowProviderDetailByUserId(String providerId) {
        try{
            Provider providerDetail = providerRepository.findProviderById(providerId);
            List<OfferDto> orderDetail = offerRepository.findOfferAndRequestByProviderIdAndStatus(providerId, 8);
            ProviderDetailDto providerDetailDto = new ProviderDetailDto();
            providerDetailDto.setProviderName(userService.getUserNameById(providerDetail.getUserId()));
            providerDetailDto.setLocationName(locationService.getLocationNameByLocationId(providerDetail.getLocationId()));
            providerDetailDto.setStarOfAttitude(providerDetail.getStarOfAttitude());
            providerDetailDto.setStarOfEfficiency(providerDetail.getStarOfEfficiency());
            providerDetailDto.setNumberOfReviews(providerDetail.getNumberOfReviews());

            List<ReviewsDto> reviewsDtos = new ArrayList<>();

                if (orderDetail == null) {
                    providerDetailDto.setReviews(reviewsDtos);
                } else {
                    for (OfferDto offer : orderDetail) {
                        ReviewsDto reviewsDto = new ReviewsDto();
                        String orderId = orderRepository.findOrderIdByOfferId(offer.getOfferId());
                        Review review = reviewRepository.findReviewByOrderId(orderId);
                        if (review == null) {
                            logger.info("review is null");
                            continue;
                        }
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

    //GET : /api/provider/dashboard
    public ProviderDashboardDto getProviderDashboard(String userId) {
        try{
            var providerId = providerRepository.getIdByUserId(userId);
            Provider provider = providerRepository.findProviderById(providerId);
            Integer numberOfOffer = offerRepository.getPendingOfferByProviderId(providerId);
            Integer numberOfActiveTask = orderRepository.getActiveOrderByProviderId(providerId);
            Integer numberOfTaskCompleted = orderRepository.getCompletedOrderByProviderId(providerId);
            Integer balance = orderRepository.calculateBalanceByProviderId(providerId);
            ProviderDashboardDto DashBoard = new ProviderDashboardDto();
            DashBoard.setAverageAttitude(provider.getStarOfAttitude());
            DashBoard.setAverageEfficiency(provider.getStarOfEfficiency());
            DashBoard.setReviewCount(provider.getNumberOfReviews());
            DashBoard.setOfferCount(numberOfOffer);
            DashBoard.setActiveTaskCount(numberOfActiveTask);
            DashBoard.setCompletedTaskCount(numberOfTaskCompleted);
            DashBoard.setBalance(balance);
            if (provider.getBiography() == null) {
                DashBoard.setBiography(null);
            } else {
                DashBoard.setBiography(provider.getBiography());
            }
            return DashBoard;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get provider dashboard");
        }
    }

    //POST : /api/provider
    @Transactional
    public String createProvider(String userId, Integer locationId, MultipartFile addressDocument, MultipartFile idDocument, MultipartFile bankDocument) {
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
            provider.setLocationId(locationId);
            ProviderVerification providerVerificationId = providerVerificationRepository.saveAndFlush(provider);
            return providerVerificationId.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot create provider application");
        }
    }

    //PUT : /api/provider/profile
    public void changeProviderBio(String userId, String bio) {
        try{
            userService.validateUserId((userId));
            Provider provider = providerRepository.findProviderByUserId(userId);
            provider.setBiography(bio);
            providerRepository.saveAndFlush(provider);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot change provider bio");
        }
    }
}
