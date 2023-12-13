package com.linkmart.dtos;

public class UserAddressFullDto {

    private String address;
    private Boolean isPrimary;

    public UserAddressFullDto(String address, Boolean isPrimary) {
        this.address = address;
        this.isPrimary = isPrimary;
    }

    @Override
    public String toString() {
        return "UserAddressFullDto{" +
                "address='" + address + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
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
