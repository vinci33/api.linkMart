package com.linkmart.repositories;

import com.linkmart.models.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Integer> {
}
