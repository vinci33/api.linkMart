package com.linkmart.dtos;

public class UserAddressFullDto {
    private Integer addressId;
    private String address;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAddressFullDto{" +
                "addressId=" + addressId +
                ", address='" + address + '\'' +
                '}';
    }
}
