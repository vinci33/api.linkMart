package com.linkmart.controllers;

import com.linkmart.dtos.UserAddressFullDto;
import com.linkmart.mappers.UserAddressFullMapper;
import com.linkmart.models.UserAddress;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")

public class UserAddressController {
        @Autowired
        UserAddressService userAddressService;
        @Autowired
        HttpServletRequest request;

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        @GetMapping("/addressInArrayFormat")
        public List<Map<String, List<String>>>  getUserAddress(HttpServletRequest request) {
            try {
                var userId = (String)request.getAttribute("userId");

                List<Map<String, List<String>>>  getAllUserAddress = userAddressService.findUserAddressByUserId("1");
                return getAllUserAddress;
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
            }
        }

        @GetMapping("/addressInJsonFormat")
        public List<UserAddressFullDto> getUserAddressInJson(HttpServletRequest request) {
            try {
                var userId = (String)request.getAttribute("userId");

                List<UserAddress>  getAllUserAddress = userAddressService.findUserAddressByUserIdInJson("1");
                return UserAddressFullMapper.INSTANCE.toUserAddressFullDtos(getAllUserAddress);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
            }

        }

}
