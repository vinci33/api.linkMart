package com.linkmart.services;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.forms.UserAddressForm;
import com.linkmart.mappers.UserAddressMapper;
import com.linkmart.models.UserAddress;
import com.linkmart.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;

    public void validateUserAddressId(Integer addressId) {
        var userAddressByAddressId = userAddressRepository.findUserAddressById(addressId);
        System.out.println("userAddressByAddressId"+userAddressByAddressId);
        if (userAddressByAddressId.isEmpty()) {
            throw new IllegalArgumentException("Invalid AddressId ");
        }
    }

    public void validateUserId(String userId) {
        var userAddressByUserId = userAddressRepository.findUserAddressByUserId(userId);
        if (userAddressByUserId.isEmpty()) {
            throw new IllegalArgumentException("Invalid UserId ");
        }
    }

    public void validateUserAddressIsPrimary(String userId){
        validateUserId(userId);
        var userAddresses = userAddressRepository.findUserAddressByUserId(userId);
        boolean anyPrimary = userAddresses.stream().anyMatch(UserAddress::isPrimary);
        if (!anyPrimary) {
            UserAddress latestUserAddress = userAddresses.stream()
                    .max(Comparator.comparing(UserAddress::getCreatedAt))
                    .orElseThrow(() -> new IllegalArgumentException("No UserAddress found"));
            latestUserAddress.setPrimary(true);
            latestUserAddress.setUpdatedAt(Timestamp.from(Instant.now()));
            userAddressRepository.saveAndFlush(latestUserAddress);
        }
    }

    // /addressInArrayFormat
    public List<Map<String, List<String>>>  findUserAddressByUserId(String userId) {
        validateUserId(userId);
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
        validateUserId(userId);
        return userAddressRepository.findUserAddressByUserId(userId);
    }

    public void putUserAddressByAddressId(Integer addressId, String userId) {
        validateUserAddressId(addressId);
        validateUserId(userId);
        var userAddresses = userAddressRepository.findUserAddressByUserId(userId);
        for (UserAddress userAddress : userAddresses) {
            boolean newIsPrimary = userAddress.getId() == (addressId);
            if (userAddress.isPrimary() != newIsPrimary) {
                userAddress.setPrimary(newIsPrimary);
                userAddress.setUpdatedAt(Timestamp.from(Instant.now()));
                userAddressRepository.saveAndFlush(userAddress);
            }

        }
    }

    public void deleteUserAddressByAddressId(Integer addressId, String userId) {
        validateUserAddressId(addressId);
        validateUserId(userId);
        userAddressRepository.deleteUserAddressByIdAndUserId(addressId,userId);
        validateUserAddressIsPrimary(userId);
    }

    public void createUserAddress(UserAddressForm userAddressForm, String userId) {
            validateUserId(userId);
          UserAddress userAddress = new UserAddress();
          userAddress.setUserId(userId);
          userAddress.setAddress(userAddressForm.getAddress());
          userAddress.setPrimary(true);
          userAddressRepository.saveAndFlush(userAddress);
    }
}


