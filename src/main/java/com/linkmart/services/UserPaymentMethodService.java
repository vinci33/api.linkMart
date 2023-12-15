package com.linkmart.services;


import com.linkmart.dtos.UserPaymentMethodDto;
import com.linkmart.forms.UserPaymentMethodForm;
import com.linkmart.models.UserPaymentMethod;
import com.linkmart.repositories.UserPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class UserPaymentMethodService {

    @Autowired
    private UserPaymentMethodRepository userPaymentMethodRepository;

    public UserPaymentMethodService(UserPaymentMethodRepository userPaymentMethodRepository) {
        this.userPaymentMethodRepository = userPaymentMethodRepository;
    }

    public void validateUserPaymentMethodId(Integer paymentMethodId) {
        var userPaymentMethodByPaymentMethodId = userPaymentMethodRepository.findUserPaymentMethodById(paymentMethodId);
        System.out.println("userPaymentMethodByPaymentMethodId"+userPaymentMethodByPaymentMethodId);
        if (userPaymentMethodByPaymentMethodId.isEmpty()) {
            throw new IllegalArgumentException("Invalid PaymentMethodId ");
        }
    }

    public void validateUserId(String userId) {
        var userPaymentMethodByUserId = userPaymentMethodRepository.findUserPaymentMethodByUserId(userId);
        if (userPaymentMethodByUserId.isEmpty()) {
            throw new IllegalArgumentException("Invalid UserId ");
        }
    }

    public List<UserPaymentMethod> findUserPaymentMethodByUserId(String userId) {
        validateUserId((userId));
        return userPaymentMethodRepository.findUserPaymentMethodByUserId(userId);

    }

    public void createUserPaymentMethod(String userId, UserPaymentMethodForm userPaymentMethodForm) {
        validateUserId(userId);
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
        userPaymentMethod.setUserId(userId);
        userPaymentMethod.setPaymentMethod(userPaymentMethodForm.getPayment_method());
        userPaymentMethod.setCardNo(userPaymentMethodForm.getCard_no());
        userPaymentMethod.setCardHolderName(userPaymentMethodForm.getCard_holder_name());
        userPaymentMethod.setExpiryDate(userPaymentMethodForm.getExpiry_date());
        userPaymentMethodRepository.saveAndFlush(userPaymentMethod);
    }
}
