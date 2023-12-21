package com.linkmart.mappers;

import com.linkmart.dtos.OrdersByOrderIdDto;
import com.linkmart.dtos.OrdersByOrderIdImageDto;
import com.linkmart.dtos.OrdersByOrderIdWithoutImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

        OrdersByOrderIdDto toOrdersByOrderIdDto(OrdersByOrderIdWithoutImageDto withoutImageDto);
}
