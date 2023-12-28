package com.linkmart.services;

import com.linkmart.models.Location;
import com.linkmart.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public void validateLocationId(Integer locationId) {
        var locationByLocationId = locationRepository.findLocationById(locationId);
        if (locationByLocationId == null ) {
            throw new IllegalArgumentException("Invalid LocationId" + locationId);
        }
    }

    public String getLocationNameByLocationId(Integer locationId) {
        var locationNameByLocationId = locationRepository.findLocationNameById(locationId);
        if (locationNameByLocationId  == null ) {
            throw new IllegalArgumentException("Invalid LocationId ");
        }
        return locationNameByLocationId ;
    }
}
