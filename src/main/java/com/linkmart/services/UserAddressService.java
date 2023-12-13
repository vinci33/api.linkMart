package com.linkmart.services;

import com.linkmart.dtos.AddressList;
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

    public List<AddressList> findUserAddressByUserId(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findUserAddressByUserId(userId);
        System.out.println("userAddresses: " + userAddresses);
        List<UserAddressDto> userAddressDtos = new ArrayList<>();
        for (UserAddress userAddress : userAddresses) {
            UserAddressDto dto = new UserAddressDto(userAddress.getAddress());
            userAddressDtos.add(dto);
        }
        System.out.println("userAddressDtos: " + userAddressDtos);
        List<AddressList> addressLists = UserAddressMapper.INSTANCE.toUserAddressDtos(userAddressDtos);
        System.out.println("addressLists: " + addressLists);


        return addressLists; // is_primary will be the first element
    }

}
