package com.linkmart.controllers;

import com.linkmart.dtos.OrderStatusDto;
import com.linkmart.dtos.StatusDto;
import com.linkmart.mappers.OrderStatusMapper;
import com.linkmart.mappers.StatusMapper;
import com.linkmart.models.OrderStatus;
import com.linkmart.models.Status;
import com.linkmart.repositories.OrderStatusRepository;
import com.linkmart.repositories.StatusRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "Status")
@RestController
@RequestMapping(value = "")
public class OrderStatusController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final OrderStatusRepository orderStatusRepository;
    public OrderStatusController (OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    };

    @Operation(summary = "Get all order status",
            description = "Get a list of OrderStatus objects for Orders entity",
            tags = { "Status","Get" })
    @GetMapping(value = "/orderStatus")
    public List<OrderStatusDto> getAllCategory (){
        List<OrderStatus> result = orderStatusRepository.findAll();
        return OrderStatusMapper.INSTANCE.getAllOrderStatus(result);
    }
}
