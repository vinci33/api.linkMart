package com.linkmart.services;

import com.linkmart.dtos.GetOneOfferDto;
import com.linkmart.dtos.OfferDto;
import com.linkmart.models.Offer;
import com.linkmart.models.Provider;
import com.linkmart.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Transactional
    public void postOffer(String userId, String requestId,
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
            var thisRequest = requestRepository.findCreatedByByRequestId(requestId);
            logger.info("thisRequest: " + thisRequest);
            // check if request exists and not created by this provider
            if (thisRequest == null) {
                throw new Exception("Request not found");
            } else if (thisRequest.equals(userId)) {
                throw new Exception("Cannot offer to your own request");
            }
            Offer offer = new Offer();
            offer.makeOfferCase();
            offer.setProviderId(providerId);
            offer.setOfferStatusId(1);
            offer.setRequestId(requestId);
            offer.setPrice(price);
            offer.setEstimatedProcessTime(estimatedProcessTime);
            offer.setOfferRemark(offerRemark);
            requestRepository.updateRequestStatusIdByRequestId(requestId);
            offerRepository.saveAndFlush(offer);
        } catch (Exception e) {
            throw new Exception("Cannot create offer in database");
        }
    }

    @Transactional
    public List<GetOneOfferDto> getOfferByRequestId(String userId, String requestId) throws Exception {
        try {
            var providerId = providerRepository.getIdByUserId(userId);
            logger.info("providerId: " + providerId);
            // check if provider exists
            if (providerId == null) {
                throw new Exception("Provider not found");
            }
            var thisRequest = requestRepository.findCreatedByByRequestId(requestId);
            logger.info("thisRequest: " + thisRequest);
            // check if request exists and not created by this provider
            if (thisRequest == null) {
                throw new Exception("Request not found");
            } else if (thisRequest.equals(userId)) {
                throw new Exception("Cannot offer to your own request");
            }
            Offer offer = offerRepository.findByRequestId(requestId);
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
            throw new Exception("Cannot get offer in database");
        }
    }

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
            return offer;
        } catch (Exception e) {
            throw new Exception("Cannot get offer in database");
        }
    }
}
