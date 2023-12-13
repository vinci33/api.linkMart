package com.linkmart.dtos;

import java.util.List;

public class UserAddressDto {
    private String address;

    public UserAddressDto(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAddressDto{" +
                "address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
