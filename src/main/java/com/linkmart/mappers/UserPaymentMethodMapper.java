package com.linkmart.mappers;

import com.linkmart.dtos.UserPaymentMethodDto;
import com.linkmart.models.UserPaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserPaymentMethodMapper {
    UserPaymentMethodMapper INSTANCE = Mappers.getMapper(UserPaymentMethodMapper.class);


    @Mapping(source = "paymentMethod", target = "payment_method")
    @Mapping(source = "cardNo", target = "card_no")
    @Mapping(source = "cardHolderName", target = "card_holder_name")
    @Mapping(source = "expiryDate", target = "expiry_date")
    UserPaymentMethodDto toUserPaymentMethodDto(UserPaymentMethod userPaymentMethod);
    List<UserPaymentMethodDto> toUserPaymentMethodDtos(Iterable<UserPaymentMethod> userPaymentMethods);
}
