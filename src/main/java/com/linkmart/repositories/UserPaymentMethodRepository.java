package com.linkmart.repositories;

import com.linkmart.models.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Integer> {
    List<UserPaymentMethod> findUserPaymentMethodById(Integer paymentMethodId);


    List<UserPaymentMethod> findUserPaymentMethodByUserId(String userId);

}
