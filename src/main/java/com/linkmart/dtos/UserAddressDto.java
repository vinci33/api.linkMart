package com.linkmart.dtos;

public class UserAddressDto {
    private String address;
    private Boolean isPrimary;

    public UserAddressDto(String address, Boolean isPrimary) {
        this.address = address;
        this.isPrimary = isPrimary;
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
