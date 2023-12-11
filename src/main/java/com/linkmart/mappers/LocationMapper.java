package com.linkmart.mappers;

import com.linkmart.dtos.LocationDto;
import com.linkmart.models.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    List<LocationDto> getAllLocation (List<Location> OrderStatusList);
}
