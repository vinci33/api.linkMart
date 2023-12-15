package com.linkmart.forms;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserAddressForm {

    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public UserAddressForm(@JsonProperty("address")String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "UserAddressForm{" +
                "address='" + address + '\'' +
                '}';
    }
}
