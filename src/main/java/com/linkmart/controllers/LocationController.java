package com.linkmart.controllers;

import com.linkmart.dtos.LocationDto;
import com.linkmart.mappers.LocationMapper;
import com.linkmart.models.Location;
import com.linkmart.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class LocationController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocationRepository locationRepository;

    @GetMapping(value = "/location")
    public List<LocationDto> getAllLocation (){
        List<Location> result = locationRepository.findAll();
        return LocationMapper.INSTANCE.getAllLocation(result);
    }
}
