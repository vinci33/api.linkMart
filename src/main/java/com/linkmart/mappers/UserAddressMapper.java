package com.linkmart.mappers;

import com.linkmart.dtos.AddressList;
import com.linkmart.dtos.UserAddressDto;
import com.linkmart.models.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface UserAddressMapper {

    UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);
//    @Mapping(source = "address", target = "address")
//    AddressList userAddressDtoToAddressList(UserAddressDto userAddressDto);

//    List<AddressList> toUserAddressDtos(List<UserAddressDto> userAddressDtos);



}
