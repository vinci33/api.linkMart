package com.linkmart.controllers;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.models.UserAddress;
import com.linkmart.service.UserAddressService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")

public class UserAddressController {

        @Autowired
        UserAddressService userAddressService;
        @Autowired
        HttpServletRequest request;

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        @GetMapping("/address")
        public List<UserAddressDto> getUserAddress() {
            try {
                var userId = (String)request.getAttribute("userId");
               List<UserAddressDto> userAddressDtos = userAddressService.findUserAddressByUserId(userId);
                return userAddressDtos;

            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
            }

        }




}
