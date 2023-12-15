package com.linkmart.controllers;


import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.dtos.UserPaymentMethodDto;
import com.linkmart.models.UserPaymentMethod;
import com.linkmart.services.UserPaymentMethodService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.linkmart.mappers.UserPaymentMethodMapper;
import com.linkmart.forms.UserPaymentMethodForm;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserPaymentMethodController {
    @Autowired
    UserPaymentMethodService userPaymentMethodService;
    @Autowired
    HttpServletRequest request;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping ("/payment")
    public List<UserPaymentMethodDto> getUserPaymentMethod(HttpServletRequest request) {
        try {
            var userId = (String)request.getAttribute("userId");
            System.out.println("controller"+userId);
            List<UserPaymentMethod> userPaymentMethod = userPaymentMethodService.findUserPaymentMethodByUserId(userId);
            return UserPaymentMethodMapper.INSTANCE.toUserPaymentMethodDtos(userPaymentMethod);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/payment")
    public ResponseWithMessage createUserPaymentMethod(HttpServletRequest request, @RequestBody UserPaymentMethodForm userPaymentMethodForm) {
        if (userPaymentMethodForm == null || userPaymentMethodForm.getPayment_method() == null || userPaymentMethodForm.getPayment_method().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }
        try {
            var userId = (String) request.getAttribute("userId");
            System.out.println("controller/post/payment" + userId);
            userPaymentMethodService.createUserPaymentMethod(userId, userPaymentMethodForm);
            return new ResponseWithMessage(true, "User Payment Method had been created");

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    @PutMapping("/payment/{paymentMethodId}")
    public ResponseWithMessage updatePaymentMethodByPaymentMethodId(@PathVariable Integer paymentMethodId,
                                                                    @RequestBody UserPaymentMethodForm userPaymentMethodForm,
                                                                    HttpServletRequest request) {
        if (userPaymentMethodForm == null || userPaymentMethodForm.getPayment_method() == null || userPaymentMethodForm.getPayment_method().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }
        try {
            var userId = (String)request.getAttribute("userId");
            userPaymentMethodService.updatePaymentMethodByPaymentMethodId(paymentMethodId, userId, userPaymentMethodForm);
            return new ResponseWithMessage(true, "User Payment Method had been updated");
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/payment/{paymentMethodId}")
    public ResponseWithMessage deleteUserPaymentMethodById(@PathVariable Integer paymentMethodId, HttpServletRequest request) {
        try {
            var userId = (String)request.getAttribute("userId");
            userPaymentMethodService.deleteUserPaymentMethodByPaymentMethodId(paymentMethodId, userId);
            return new ResponseWithMessage(true, "User Payment Method had been deleted");
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
