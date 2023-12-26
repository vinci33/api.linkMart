package com.linkmart.controllers;

import com.linkmart.dtos.LogisticCompanyDto;
import com.linkmart.dtos.LogisticCompanyIdDto;
import com.linkmart.forms.LogisticCompanyForm;
import com.linkmart.mappers.LogisticCompanyMapper;
import com.linkmart.models.LogisticCompany;
import com.linkmart.repositories.LogisticCompanyRepository;
import com.linkmart.services.LogisticCompanyService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class LogisticCompanyController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogisticCompanyRepository logisticCompanyRepository;

    @Autowired
    LogisticCompanyService logisticCompanyService;

    @GetMapping(value = "/logisticCompany")
    public List<LogisticCompanyDto> getAllLogisticCompany (){
        List<LogisticCompany> result = logisticCompanyRepository.findAll();
        return LogisticCompanyMapper.INSTANCE.getAllLogisticCompany(result);
    }

    @PostMapping(value = "/api/logisticCompany")
    public LogisticCompanyIdDto uploadLogisticCompany (@RequestBody LogisticCompanyForm companyForm,
                                                       HttpServletRequest request)
            throws Exception {
        try {
            var userId = (String)request.getAttribute("userId");
            var logisticId = logisticCompanyService.uploadLogisticCompany(companyForm, userId);
            return new LogisticCompanyIdDto(logisticId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
