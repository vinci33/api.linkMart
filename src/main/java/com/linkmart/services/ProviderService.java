package com.linkmart.services;

import com.linkmart.models.Provider;
import com.linkmart.repositories.LocationRepository;
import com.linkmart.repositories.ProviderRepository;
import com.linkmart.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProviderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProviderRepository providerRepository;



    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;


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
}
