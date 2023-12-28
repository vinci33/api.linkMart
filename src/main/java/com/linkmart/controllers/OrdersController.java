package com.linkmart.controllers;


import com.linkmart.dtos.OrderPaymentDto;
import com.linkmart.dtos.OrdersByOrderIdDto;
import com.linkmart.dtos.OrdersByOrderIdAndStatusDto;
import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.forms.ReportCasesForm;
import com.linkmart.forms.ReviewForm;
import com.linkmart.mappers.OrdersByOrderIdAndStatusMapper;
import com.linkmart.repositories.OrdersRepository;
import com.linkmart.services.OrdersService;
import com.linkmart.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Tag(name = "Orders",description = """
               **Orders APIs**
               - Represents the OrdersController class, which is responsible for handling various API endpoints related to orders involving a provider, a user, a request, and an offer.
               
               - It interacts with the OrdersService and ReportService classes to perform operations such as creating an order,
                retrieving orders by user ID and status, updating an order, reviewing an order, reviewing order and report order case.
                """)
@RestController
@RequestMapping(value = "/api")
public class OrdersController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrdersService ordersService;

    private final HttpServletRequest request;

    private final ReportService reportService;

    private final OrdersRepository ordersRepository;

    private String lastCompletedOrderId;

    public OrdersController (OrdersService ordersService, HttpServletRequest request, ReportService reportService, OrdersRepository ordersRepository){
        this.ordersService = ordersService;
        this.ordersRepository = ordersRepository;
        this.request = request;
        this.reportService = reportService;
    };


    @Operation(summary = "Create an order",
            description = "Creates an order with offerId and userAddressId by success or cancelled bollean",
            tags = { "Orders","Get"})
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



    @Operation(summary = "Get all orders by user id and order status",
            description = "Retrieves orders by user ID and order status for a provider.",
            tags = { "Orders","Get"})
    //for provider find order by order status
    @GetMapping(value = "/provider/order/{orderStatus}")
    public List<OrdersByOrderIdAndStatusDto> providerGetOrdersByUserId(@PathVariable String orderStatus){
        try{
            if (orderStatus == null) {
                throw new IllegalArgumentException("OrderStatus not found");
            }
            List<String> statuses = new ArrayList<>();
            if ("inProgress".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("in-progress", "shipped");
            } else if ("complete".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("completed", "cancelled", "reviewed");
            } else {
                throw new IllegalArgumentException("Invalid orderStatus: " + orderStatus);
            }
            var userId = (String)request.getAttribute("userId");
            var ordersDtos = ordersService.getOrdersByUserIdAndStatus(userId, statuses);
            return OrdersByOrderIdAndStatusMapper.INSTANCE.toOrdersByOrderIdAndStatusDtos(ordersDtos);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @Operation(summary = "Get all orders by user id and order status",
            description = "Retrieves orders by user ID and order status for a user.",
            tags = { "Orders","Get"})
    //for user find order by order status
    @GetMapping(value = "/user/order/{orderStatus}")
    public List<OrdersByOrderIdAndStatusDto> userGetOrdersByUserId(@PathVariable String orderStatus){
        try{
            if (orderStatus == null) {
                throw new IllegalArgumentException("OrderStatus not found");
            }
            List<String> statuses = new ArrayList<>();
            if ("inProgress".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("in-progress", "shipped");
            } else if ("complete".equalsIgnoreCase(orderStatus)) {
                statuses = Arrays.asList("completed", "cancelled", "reviewed");
            } else {
                throw new IllegalArgumentException("Invalid orderStatus: " + orderStatus);
            }
            var userId = (String)request.getAttribute("userId");
            var ordersDtos = ordersService.userGetOrdersByUserIdAndStatusFromUser(userId, statuses);
            return OrdersByOrderIdAndStatusMapper.INSTANCE.toOrdersByOrderIdAndStatusDtos(ordersDtos);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Get order by order id",
            description = "Retrieves orders by order ID.",
            tags = { "Orders","Get"})
    @GetMapping(value = "/order/{orderId}")
    public OrdersByOrderIdDto getOrdersDetailByOrderId (@PathVariable String orderId) {
        try {
            if (orderId == null) {
                throw new IllegalArgumentException("OrderId not found 1");
            }
            var result = ordersService.getOrdersDetailByOrderId(orderId);
            return result;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Update order shipping order id",
            description = "Updates the shipping details of an order and start the schedule function.",
            tags = { "Orders","Put"})
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


    @Operation(summary = "Update order received",
            description = "Updates the status of an order to received.",
            tags = { "Orders","Put"})
    @PutMapping(value = "/order/{orderId}/received")
    public void updateOrderReceived(@PathVariable String orderId) {
        try {
            if (orderId == null ) {
                throw new IllegalArgumentException("Order Id not found");
            }
            var userId = (String)request.getAttribute("userId");
            ordersService.updateOrderReceived(orderId, userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private final SseEmitter emitter = new SseEmitter();

    @EventListener
    public void orderCompleteEventHandler(String orderId){
        try {
            lastCompletedOrderId = orderId;
            emitter.send(Map.of("OrderId " , orderId));
        }catch (IOException e){
            emitter.completeWithError(e);
        }
    }

    @Operation(summary = "Get order status",
            description = """
                    Retrieve the order status using Server-Sent Events (SSE).
                    It returns a order Id after validate incoming user Id.
                    Order Status will be auto update to completed after 5 minutes""",
            tags = { "Orders","Get"})
    @GetMapping(value = "/order/sse")
    public SseEmitter sseEmitter() throws IOException{
        String requestUserId = (String)request.getAttribute("userId");
        if (requestUserId  == null) {
            throw new IllegalArgumentException("UserId not found");
        }
        String orderUserId = ordersRepository.findUserIdByOrderId(lastCompletedOrderId);
        if (orderUserId == null) {
            throw new IllegalArgumentException("Order Id not found");
        }
        SseEmitter sseEmitter = new SseEmitter();
        if (!requestUserId.equals(orderUserId)) {
            sseEmitter.send("Shipping in progress");
        } else {
            sseEmitter = emitter;
        }
        return sseEmitter;
    }

    @Operation(summary = "Update order review",
            description = "Reviews an order by orderId with ReviewForm.",
            tags = { "Orders","Post"})
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

    @Operation(summary = "Create report case for order",
            description = "Report an order by orderId with ReportCasesForm.",
            tags = { "Orders","Post"})
    @PostMapping(value = "/order/report/{orderId}")
    public ResponseWithMessage reportOrder(@PathVariable String orderId,
                                           @RequestBody ReportCasesForm reportCasesForm) {
        try {
            if (orderId == null || reportCasesForm.getContent() == null || reportCasesForm.getSubject() == null) {
                throw new IllegalArgumentException("Order Id or report remark not found");
            }
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            var content = reportCasesForm.getContent();
            var subject = reportCasesForm.getSubject();
            reportService.createReportCase(orderId, content, subject);
            return new ResponseWithMessage(true, "Report success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
