package com.linkmart.controllers;

import com.linkmart.dtos.LogisticCompanyDto;
import com.linkmart.dtos.LogisticCompanyIdDto;
import com.linkmart.forms.LogisticCompanyForm;
import com.linkmart.mappers.LogisticCompanyMapper;
import com.linkmart.models.LogisticCompany;
import com.linkmart.repositories.LogisticCompanyRepository;
import com.linkmart.services.LogisticCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Logistic Company", description = """
        ***All Logistic Company APIs***
        - Represents the LogisticCompanyController class, which is responsible for handling HTTP requests related to the Logistic Company entity.
        
        - It retrieves a list of all logistic companies from the database and maps them to DTO (Data Transfer Object) objects for use by the Orders entities,
        upload and create logistic company info.
          
        """)
@RestController
@RequestMapping(value = "")
public class LogisticCompanyController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final LogisticCompanyRepository logisticCompanyRepository;


    private final LogisticCompanyService logisticCompanyService;

    public LogisticCompanyController (LogisticCompanyRepository logisticCompanyRepository,
                                      LogisticCompanyService logisticCompanyService){
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.logisticCompanyService = logisticCompanyService;
    };

    @Operation(summary = "Get all logistic company",
            description = "Retrieves a list of all logistic companies and maps them to LogisticCompanyDto .",
            tags = { "Logistic Company","Get" })
    @GetMapping(value = "/logisticCompany")
    public List<LogisticCompanyDto> getAllLogisticCompany (){
        List<LogisticCompany> result = logisticCompanyRepository.findAll();
        return LogisticCompanyMapper.INSTANCE.getAllLogisticCompany(result);
    }

    @Operation(summary = "Upload Logistic Company",
            description = "Update the Logistic Company info by provider ",
            tags = { "Logistic Company","Post" })
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
