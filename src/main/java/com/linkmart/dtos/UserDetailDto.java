package com.linkmart.dtos;

import java.util.List;

public class UserDetailDto {
    private String username;
    private String userEmail;
    private List<UserAddressDto> userAddress;
    private List<UserPaymentMethodDto> userPaymentMethod;

    @Override
    public String toString() {
        return "UserDetailDto{" +
                "username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress=" + userAddress +
                ", userPaymentMethod=" + userPaymentMethod +
                '}';
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<UserAddressDto> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<UserAddressDto> userAddress) {
        this.userAddress = userAddress;
    }

    public List<UserPaymentMethodDto> getUserPaymentMethod() {
        return userPaymentMethod;
    }

    public void setUserPaymentMethod(List<UserPaymentMethodDto> userPaymentMethod) {
        this.userPaymentMethod = userPaymentMethod;
    }

}
