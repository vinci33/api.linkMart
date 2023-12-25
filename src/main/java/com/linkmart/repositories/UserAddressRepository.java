package com.linkmart.repositories;

import com.linkmart.models.UserAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {



    @Query(value = "SELECT * FROM user_address WHERE user_id = ?1 ORDER BY is_primary DESC", nativeQuery = true)
    List<UserAddress> findUserAddressByUserId(String userId);

    List<UserAddress> findUserAddressById(Integer Id);

    @Query(value =
            """
             SELECT address FROM user_address WHERE id = :addressId
             """
            ,nativeQuery = true)
    String findAddressById(@Param("addressId") Integer Id);

    @Transactional
    void deleteUserAddressByIdAndUserId(Integer id, String userId);



}

