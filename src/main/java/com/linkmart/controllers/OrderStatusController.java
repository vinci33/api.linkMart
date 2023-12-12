package com.linkmart.controllers;

import com.linkmart.dtos.OrderStatusDto;
import com.linkmart.dtos.StatusDto;
import com.linkmart.mappers.OrderStatusMapper;
import com.linkmart.mappers.StatusMapper;
import com.linkmart.models.OrderStatus;
import com.linkmart.models.Status;
import com.linkmart.repositories.OrderStatusRepository;
import com.linkmart.repositories.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class OrderStatusController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @GetMapping(value = "/orderStatus")
    public List<OrderStatusDto> getAllCategory (){
        List<OrderStatus> result = orderStatusRepository.findAll();
        return OrderStatusMapper.INSTANCE.getAllOrderStatus(result);
    }
}
