package com.linkmart.forms;

import jakarta.validation.constraints.NotNull;

public class UserAddressForm {
    @NotNull
    private String address;

    public String getAddress() {
        return address;
    }

    public UserAddressForm(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "UserAddressForm{" +
                "address='" + address + '\'' +
                '}';
    }
}
