package com.linkmart.controllers;

import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.forms.ImageForm;
import com.linkmart.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(name = "Request")
@RestController
public class ImageController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageService imageService;

    @Operation(summary = "Change request image",
            description = """
            This method is used to change the request image.
            It takes the imageId as a path variable and calls the updateRequestImage method of the ImageService class.
            It returns a RequestResponseWithMessageDto object with a success message if the image is successfully updated, otherwise it throws a ResponseStatusException with a bad request status.
            """,
            tags = { "Request","Put"})
    @PutMapping(value = "/api/request/image/{imageId}")
    public RequestResponseWithMessageDto changeRequestImage(@PathVariable(value = "imageId") Integer imageId) {
        try {
            imageService.updateRequestImage(imageId);
            return new RequestResponseWithMessageDto("Delete image success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delete image failed");
        }
    }

    @Operation(summary = "Set primary image",
            description = """
            This method is used to set the primary image.
            It takes an ImageForm object and a HttpServletRequest object as parameters.
            It retrieves the userId from the request attribute, calls the setPrimaryImage method of the ImageService class, and returns a RequestResponseWithMessageDto object with a success message if the primary image is successfully set, otherwise it throws a ResponseStatusException with a bad request status.
            """,
            tags = { "Request","Put"})
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
