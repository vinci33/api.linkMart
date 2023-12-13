package com.linkmart.dtos;

import java.util.List;

public class UserAddressDto {
    private String address;
    private Boolean isPrimary;

    private List<String> Addresses;
    public UserAddressDto(String address, Boolean isPrimary) {
        this.address = address;
        this.isPrimary = isPrimary;
    }

    public UserAddressDto(String address) {
        this.address = address;
    }

    public UserAddressDto(List<String> addresses) {
        Addresses = addresses;
    }

    public List<String> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<String> addresses) {
        Addresses = addresses;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
