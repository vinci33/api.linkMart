package com.linkmart.controllers;

import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.forms.ImageForm;
import com.linkmart.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping(value = "/api/request/image")
    public RequestResponseWithMessageDto setPrimaryImage(@RequestBody ImageForm imageForm, HttpServletRequest request) {
        try {
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            logger.info("imageId: " + imageForm.getImageId());
            imageService.setPrimaryImage(imageForm.getImageId());
            return new RequestResponseWithMessageDto("Set primary image success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Set primary image failed");
        }
    }
}
