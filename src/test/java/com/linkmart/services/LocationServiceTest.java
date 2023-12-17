package com.linkmart.services;

import com.linkmart.repositories.LocationRepository;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationServiceTest {
    @Autowired
    LocationService locationService;

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void testGetLocationNameByLocationId() {
        Integer locationId = 1; // replace with a valid location ID
        String locationName = locationService.getLocationNameByLocationId(locationId);
        System.out.println("Location Name: " + locationName);
    }

    @Test
    public void testFindLocationNameById() {
        Integer locationId = 1; // replace with a valid location ID
        String locationName = locationRepository.findByLocationId(locationId);
        System.out.println("Location Name: " + locationName);
    }

}
