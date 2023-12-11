package com.linkmart.mappers;

import com.linkmart.dtos.CategoryFieldDto;
import com.linkmart.dtos.StatusDto;
import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusMapper {
    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    List<StatusDto> getAllStatus (List<Status> statusList);
}
