package com.linkmart.controllers;

import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.services.ProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api")
public class ProviderController {
    @Autowired
    ProviderService providerService;

    @Autowired
    HttpServletRequest request;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/provider/{locationId}")
    public ResponseWithMessage enrollProvider(@PathVariable Integer locationId, HttpServletRequest request) {
      try {
          var userId = (String)request.getAttribute("userId");
            var providerId = providerService.enrollProvider(userId,locationId);
            return new ResponseWithMessage(true, "Provider had been created ProviderId: " + providerId);
        }catch (IllegalArgumentException e) {
          logger.error(e.getMessage());
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }

    }
}
