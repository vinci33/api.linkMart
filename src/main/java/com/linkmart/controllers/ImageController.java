package com.linkmart.controllers;

import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class ImageController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageService imageService;

    @PutMapping(value = "/api/request/image/{imageId}")
    public RequestResponseWithMessageDto changeRequestImage(@PathVariable(value = "imageId") Integer imageId) {
        try {
            imageService.updateRequestImage(imageId);
            return new RequestResponseWithMessageDto("Delete image success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delete image failed");
        }
    }

}
