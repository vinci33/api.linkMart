package com.linkmart.controllers;


import com.linkmart.dtos.OrderPaymentDto;
import com.linkmart.dtos.OrdersByOrderIdDto;
import com.linkmart.dtos.OrdersDtoWithDays;
import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.forms.OrdersForm;
import com.linkmart.forms.ReviewForm;
import com.linkmart.forms.UpdateOrderForm;
import com.linkmart.services.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @Autowired
    HttpServletRequest request;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/order")
    public OrderPaymentDto createOrder(
            @RequestParam(value = "success", required = false) Boolean success,
            @RequestParam(value = "cancelled", required = false) Boolean cancelled,
            @RequestParam(value = "offerId") String offerId,
            @RequestParam(value = "userAddressId") Integer userAddressId
    ) {
        try {
            if ((success == null && cancelled == null) || (success != null && cancelled != null)) {
                throw new IllegalArgumentException("Invalid status");
            }
            if ((cancelled != null && cancelled)){
                throw new IllegalArgumentException("payment cancelled");
            }
            if (offerId == null || userAddressId == null) {
                throw new IllegalArgumentException("OfferId or userAddressId not found");
            }
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            var orderId = ordersService.createOrder(success ,userId, offerId, userAddressId);
            return new OrderPaymentDto(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/user/order")
    public List<OrdersDtoWithDays> getOrdersByUserId() {
        try {
            var userId = (String) request.getAttribute("userId");
            return ordersService.getOrdersByUserId(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //for provider find order by order status
    @GetMapping(value = "/provider/order/{orderStatus}")
    public List<OrdersDtoWithDays> providerGetOrdersByUserId( @PathVariable String orderStatus){
        try{
            if (orderStatus == null) {
                throw new IllegalArgumentException("OrderStatus not found");
            }
            List<String> statuses = new ArrayList<>();
            if ("inProgress".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("in-progress", "shipped");
            } else if ("complete".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("completed", "cancelled");
            } else {
                throw new IllegalArgumentException("Invalid orderStatus: " + orderStatus);
            }
            var userId = (String)request.getAttribute("userId");
            return ordersService.getOrdersByUserIdAndStatus(userId, statuses);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //for user find order by order status
    @GetMapping(value = "/user/order/{orderStatus}")
    public List<OrdersDtoWithDays> userGetOrdersByUserId( @PathVariable String orderStatus){
        try{
            if (orderStatus == null) {
                throw new IllegalArgumentException("OrderStatus not found");
            }
            List<String> statuses = new ArrayList<>();
            if ("inProgress".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("in-progress", "shipped");
            } else if ("complete".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("completed", "cancelled");
            } else {
                throw new IllegalArgumentException("Invalid orderStatus: " + orderStatus);
            }
            var userId = (String)request.getAttribute("userId");
            return ordersService.userGetOrdersByUserIdAndStatus(userId, statuses);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/order/{orderId}")
    public OrdersByOrderIdDto getOrdersDetailByOrderId (@PathVariable String orderId) {
        try {
            if (orderId == null) {
                throw new IllegalArgumentException("OrderId not found");
            }
            var result = ordersService.getOrdersDetailByOrderId(orderId);
            return result;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/order/{orderId}")
    public void updateOrderShippingOrderId(@PathVariable String orderId,
                                           @RequestParam(value = "shipmentProof")MultipartFile file,
                                           @RequestParam(value = "shippingOrderNo")String shippingOrderNo,
                                           @RequestParam(value = "logisticCompanyId")Integer logisticCompanyId) {
        try {
            if (orderId == null ) {
                throw new IllegalArgumentException("Order Id or shipping order Id not found");
            }
            if (shippingOrderNo == null || logisticCompanyId == null || file == null) {
                throw new IllegalArgumentException("Shipping order Id or logistic company Id not found");
            }
            ordersService.updateOrderShippingOrderId(orderId, logisticCompanyId, shippingOrderNo,file);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //For requester to review order
    @PostMapping(value = "/order/{orderId}/review")
    public ResponseWithMessage reviewOrder(@PathVariable String orderId, @RequestBody ReviewForm reviewForm) {
        try {
            var efficiency = reviewForm.getEfficiency();
            var attitude = reviewForm.getAttitude();
            var reviewRemark = reviewForm.getComments();
            if (orderId == null || efficiency == null || attitude == null) {
                throw new IllegalArgumentException("Order Id or rating or review not found");
            }
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            if (reviewRemark == null) {
                reviewRemark = null;
            }
            ordersService.reviewOrder(orderId, userId, efficiency, attitude, reviewRemark);
            return new ResponseWithMessage(true, "Review success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
