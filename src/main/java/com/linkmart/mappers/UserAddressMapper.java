package com.linkmart.mappers;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.models.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAddressMapper {

    UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);

    UserAddressDto toUserAddressDto(UserAddress userAddress);

    List<UserAddressDto> toUserAddressDtos(Iterable<UserAddress> userAddress);



}
