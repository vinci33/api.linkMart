package com.linkmart.services;

import com.linkmart.dtos.UserAddressDto;
import com.linkmart.forms.UserAddressForm;
import com.linkmart.mappers.UserAddressMapper;
import com.linkmart.models.UserAddress;
import com.linkmart.repositories.UserAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAddressService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    UserService userService;

    public UserAddressService(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Transactional
    public void validateUserAddressId(Integer addressId) {
        var userAddressByAddressId = userAddressRepository.findUserAddressById(addressId);
        System.out.println("userAddressByAddressId"+userAddressByAddressId);
        if (userAddressByAddressId.isEmpty()) {
            throw new IllegalArgumentException("Invalid AddressId ");
        }
    }


    @Transactional
    public void validateUserAddressIsPrimary(String userId){
        userService.validateUserId(userId);
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

//     /addressInArrayFormat
    @Transactional
    public List<Map<String, List<String>>>  findUserAddressByUserId(String userId) {
        userService.validateUserId(userId);
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
    @Transactional
    public List<UserAddress>  findUserAddressByUserIdInJson(String userId) {
        userService.validateUserId(userId);
        return userAddressRepository.findUserAddressByUserId(userId);
    }

    @Transactional
    public void putUserAddressByAddressId(Integer addressId, String userId) {
        validateUserAddressId(addressId);
        userService.validateUserId(userId);
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

    @Transactional
    public void deleteUserAddressByAddressId(Integer addressId, String userId) {
        validateUserAddressId(addressId);
        userService.validateUserId(userId);
        userAddressRepository.deleteUserAddressByIdAndUserId(addressId,userId);
        validateUserAddressIsPrimary(userId);
    }

    public UserAddress createUserAddress(UserAddressForm userAddressForm, String userId) {
          userService.validateUserId(userId);
          UserAddress userAddress = new UserAddress();
          userAddress.setUserId(userId);
          userAddress.setAddress(userAddressForm.getAddress());
          userAddress.setPrimary(true);
          userAddressRepository.saveAndFlush(userAddress);
          return userAddress;
    }
}


