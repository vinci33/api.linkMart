package com.linkmart.controllers;

import com.linkmart.dtos.StatusDto;
import com.linkmart.mappers.StatusMapper;
import com.linkmart.models.Status;
import com.linkmart.repositories.StatusRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "Status", description = """
        ***All Status APIs***
        - Represents the StatusController and OrderStatusController classes, which is responsible for handling HTTP requests related to the Status and the orderStatus entity.
        
        - It retrieves a list of all statuses and order statuses from the database and maps them to DTO (Data Transfer Object) objects for use by the Offer and Order entities.
          
        """)
@RestController
@RequestMapping(value = "")
public class StatusController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StatusRepository statusRepository;

    public StatusController (StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    };

    @Operation(summary = "Get all status",
            description = "Get a list of Status objects for Offer entity",
            tags = { "Status","Get" })
    @GetMapping(value = "/status")
    public List<StatusDto> getAllCategory (){
        List<Status> result =  statusRepository.findAll();
        return StatusMapper.INSTANCE.getAllStatus(result);
    }
}
