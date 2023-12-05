package com.linkmart.repositories;

import com.linkmart.models.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer>{
}
