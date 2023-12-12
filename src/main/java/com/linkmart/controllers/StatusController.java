package com.linkmart.controllers;

import com.linkmart.dtos.StatusDto;
import com.linkmart.mappers.StatusMapper;
import com.linkmart.models.Status;
import com.linkmart.repositories.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class StatusController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatusRepository statusRepository;

    @GetMapping(value = "/status")
    public List<StatusDto> getAllCategory (){
        List<Status> result =  statusRepository.findAll();
        return StatusMapper.INSTANCE.getAllStatus(result);
    }
}
