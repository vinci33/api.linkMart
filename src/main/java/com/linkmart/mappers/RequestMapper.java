package com.linkmart.mappers;

import com.linkmart.dtos.RequestDto;
import com.linkmart.models.OrderStatus;
import com.linkmart.models.RequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    List<RequestDto> getAllRequest (List<RequestModel> requestModels);
}
