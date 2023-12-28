package com.linkmart.controllers;

import com.linkmart.dtos.LocationDto;
import com.linkmart.mappers.LocationMapper;
import com.linkmart.models.Location;
import com.linkmart.repositories.LocationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Location", description = """
        ***All Location APIs***
        - Represents the LocationController class, which is responsible for handling HTTP requests related to the Location entity.
        
        - It retrieves a list of all locations from the database and maps them to DTO (Data Transfer Object) objects for use by the Offer and request entities.
          
        """)
@RestController
@RequestMapping(value = "")
public class LocationController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final LocationRepository locationRepository;

    public LocationController (LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    };

    @Operation(summary = "Get all location",
            description = "Retrieves a list of all locations and maps them to LocationDto .",
            tags = { "Location","Get" })
    @GetMapping(value = "/location")
    public List<LocationDto> getAllLocation (){
        List<Location> result = locationRepository.findAll();
        return LocationMapper.INSTANCE.getAllLocation(result);
    }
}
