package com.linkmart.controllers;

import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.dtos.UserAddressFullDto;
import com.linkmart.forms.UserAddressForm;
import com.linkmart.mappers.UserAddressFullMapper;
import com.linkmart.models.UserAddress;
import com.linkmart.services.UserAddressService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
                System.out.println("controller"+userId);
                List<Map<String, List<String>>>  getAllUserAddress = userAddressService.findUserAddressByUserId(userId);
                return getAllUserAddress;
            }catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @GetMapping("/addressInJsonFormat")
        public List<UserAddressFullDto> getUserAddressInJson(HttpServletRequest request) {
            try {
                var userId = (String)request.getAttribute("userId");
                System.out.println("controller"+userId);
                List<UserAddress>  getAllUserAddress = userAddressService.findUserAddressByUserIdInJson(userId);
                return UserAddressFullMapper.INSTANCE.toUserAddressFullDtos(getAllUserAddress);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }

        @PutMapping("/address/{addressId}")
        public ResponseWithMessage changeIsPrimaryUserAddressById(@PathVariable Integer addressId, HttpServletRequest request) {
            try {
                var userId = (String)request.getAttribute("userId");
                userAddressService.putUserAddressByAddressId(addressId, userId);
                return new ResponseWithMessage(true, "User address had been updated");
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }
        @DeleteMapping("/address/{addressId}")
        public ResponseWithMessage deleteUserAddressById(@PathVariable Integer addressId, HttpServletRequest request) {
            try {
                var userId = (String)request.getAttribute("userId");
                System.out.println(userId);
                userAddressService.deleteUserAddressByAddressId(addressId, userId);
                return new ResponseWithMessage(true, "User address had been deleted, latest address is set to primary");
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }

        @RequestMapping(value = "/address", method = RequestMethod.POST)
        public ResponseWithMessage createUserAddress(HttpServletRequest request ,@RequestBody UserAddressForm userAddressForm){
            if (userAddressForm == null || userAddressForm.getAddress() == null || userAddressForm.getAddress().isEmpty()) {
                return new ResponseWithMessage(false, "UserAddressForm cannot be null or empty");
            }
            try {
                var userId = (String)request.getAttribute("userId");
                System.out.println(userId);
                userAddressService.createUserAddress(userAddressForm, userId);
                return new ResponseWithMessage(true, "User address had been created");
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

}
