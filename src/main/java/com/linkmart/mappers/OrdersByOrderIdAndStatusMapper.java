package com.linkmart.mappers;

import com.linkmart.dtos.OrdersByOrderIdAndStatusDto;
import com.linkmart.dtos.OrdersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrdersByOrderIdAndStatusMapper {
    OrdersByOrderIdAndStatusMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(OrdersByOrderIdAndStatusMapper.class);

    List<OrdersByOrderIdAndStatusDto> toOrdersByOrderIdAndStatusDtos(Iterable<OrdersDto> ordersDtos);

    OrdersByOrderIdAndStatusDto toOrdersByOrderIdAndStatusDto(OrdersDto ordersDto);

}