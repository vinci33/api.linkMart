package com.linkmart.controllers;

import com.linkmart.dtos.LogisticCompanyDto;
import com.linkmart.mappers.LogisticCompanyMapper;
import com.linkmart.models.LogisticCompany;
import com.linkmart.repositories.LogisticCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class LogisticCompanyController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogisticCompanyRepository logisticCompanyRepository;

    @GetMapping(value = "/logistic_company")
    public List<LogisticCompanyDto> getAllLocation (){
        List<LogisticCompany> result = logisticCompanyRepository.findAll();
        return LogisticCompanyMapper.INSTANCE.getAllLogisticCompany(result);
    }
}
