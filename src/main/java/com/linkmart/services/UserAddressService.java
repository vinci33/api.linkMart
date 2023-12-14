package com.linkmart.services;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.mappers.UserAddressMapper;
import com.linkmart.models.UserAddress;
import com.linkmart.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;



    public void vaildateUserAddressId(Integer addressId) {
        var userAddressByAddressId = userAddressRepository.findById()(addressId);
        if (userAddressByAddressId == null ) {
            throw new IllegalArgumentException("Invalid AddressId ");
        }
    }

    public void vaildateUserId(String userId) {
        var userAddressByUserId = userAddressRepository.findUserAddressByUserId(userId);
        if (userAddressByUserId == null ) {
            throw new IllegalArgumentException("Invalid UserId ");
        }
    }

    // /addressInArrayFormat
    public List<Map<String, List<String>>>  findUserAddressByUserId(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findUserAddressByUserId(userId);

        List<UserAddressDto> userAddressDtos = UserAddressMapper.INSTANCE.toUserAddressDtos(userAddresses);

        List<String> addresses = userAddressDtos.stream()
                .map(UserAddressDto::getAddress)
                .collect(Collectors.toList());

        Map<String, List<String>> addressMap = new HashMap<>();
        addressMap.put("address", addresses);

        return Collections.singletonList(addressMap);
    }


    //    /addressInJsonFormat
    public List<UserAddress>  findUserAddressByUserIdInJson(String userId) {
        return userAddressRepository.findUserAddressByUserId(userId);
    }

    public void putUserAddressByAddressId(String addressId, String userId) {


        userAddressRepository.putUserAddressByAddressId(addressId, userId);

    }
    }
