package com.linkmart.controllers;

import com.linkmart.forms.RequestForm;
import com.linkmart.models.Request;
import com.linkmart.repositories.RequestRepository;
import com.linkmart.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class RequestController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    S3Service s3Service;

    @PostMapping(value = "/api/request", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postRequest (@RequestBody Request request) {
        String created_by = request.getCreated_by();
        int location_id = request.getLocation_id();
        int category_id = request.getCategory_id();
        String item = request.getItem();
        String imagePath = request.getImage();
        String url = request.getUrl();

        var result = requestRepository.saveAndFlush(request);
        return "success";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test cicd success";
    }
}
