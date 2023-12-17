package com.linkmart.controllers;


import com.linkmart.services.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @Autowired
    HttpServletRequest request;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @PostMapping("/orders")
//    public Map<String> createOrder(HttpServletRequest request, @RequestBody OrdersForm ordersForm
//        try {
//            var userId = (String)request.getAttribute("userId");
//            System.out.println("controller"+userId);
//            ordersService.createOrder(userId);
//            return Map.of("orderId", orderId);
//        }catch (IllegalArgumentException e) {
//            logger.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

}
