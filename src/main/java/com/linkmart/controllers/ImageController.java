package com.linkmart.controllers;

import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.repositories.ImageRepository;
import com.linkmart.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class ImageController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageService imageService;

    //ToDo
    @DeleteMapping(value = "/api/request/image/{imageId}")
    public RequestResponseWithMessageDto deleteRequestImage(Integer imageId) {
        try {
            imageService.deleteRequestImage(imageId);
            return new RequestResponseWithMessageDto("Image deleted successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
