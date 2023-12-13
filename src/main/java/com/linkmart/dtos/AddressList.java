package com.linkmart.dtos;

import java.util.ArrayList;
import java.util.List;

public class AddressList {
    private List<String> address;

    public AddressList(List<String> address) {
        this.address = address != null ? address : new ArrayList<>();
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
