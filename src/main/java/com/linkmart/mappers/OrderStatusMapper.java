package com.linkmart.mappers;

import com.linkmart.dtos.OrderStatusDto;
import com.linkmart.dtos.StatusDto;
import com.linkmart.models.OrderStatus;
import com.linkmart.models.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface OrderStatusMapper {
    OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);

    List<OrderStatusDto> getAllOrderStatus (List<OrderStatus> OrderStatusList);
}
