package com.linkmart.services;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.mappers.UserAddressMapper;
import com.linkmart.models.UserAddress;
import com.linkmart.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;

    public List<UserAddressDto> findUserAddressByUserId(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findUserAddressByUserId(userId);

        List<UserAddressDto> userAddressDtos = new ArrayList<>();
        for (UserAddress userAddress : userAddresses) {
            UserAddressDto dto = new UserAddressDto(userAddress.getAddress());
            userAddressDtos.add(dto);
        }

        return userAddressDtos; // is_primary will be the first element
    }

}
