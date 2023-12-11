package com.linkmart.repositories;

import com.linkmart.models.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress,Integer>{
    List<UserAddress> findUserAddressByUserId(String userId);
}
