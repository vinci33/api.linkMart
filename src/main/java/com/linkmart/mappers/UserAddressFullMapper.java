package com.linkmart.mappers;
import com.linkmart.dtos.UserAddressFullDto;
import com.linkmart.models.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAddressFullMapper {
    UserAddressFullMapper INSTANCE = Mappers.getMapper(UserAddressFullMapper.class);


    @Mapping(source = "id", target = "addressId")
    UserAddressFullDto toUserAddressFullDto(UserAddress userAddress);
    List<UserAddressFullDto> toUserAddressFullDtos(Iterable<UserAddress> userAddresses);
}