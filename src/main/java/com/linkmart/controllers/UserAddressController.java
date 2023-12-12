package com.linkmart.controllers;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.services.UserAddressService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user")

public class UserAddressController {

        @Autowired
        UserAddressService userAddressService;
        @Autowired
        HttpServletRequest request;

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        @GetMapping("/address")
        public UserAddressDto getUserAddress() {
            try {
                var userId = (String)request.getAttribute("userId");
                List<UserAddressDto> getAllUserAddress = userAddressService.findUserAddressByUserId("1");
                List<String> addresses = getAllUserAddress.stream()
                        .map(UserAddressDto::getAddress)
                        .collect(Collectors.toList());
                return new UserAddressDto(addresses);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
            }

        }




}
