package com.linkmart.dtos;

public class PaymentDetailDto {
    private String offerId;
    private String userUsername;
    private String userEmail;
    private UserAddressDto userAddress;
    private UserPaymentMethodDto UserPaymentMethod;


    private String  providerUsername; //provider username
    private String location;
    private String item;
    private String primary_image;
    private String quantity;
    private Integer price; //from offer

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public UserAddressDto getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressDto userAddress) {
        this.userAddress = userAddress;
    }

    public UserPaymentMethodDto getUserPaymentMethod() {
        return UserPaymentMethod;
    }

    public void setUserPaymentMethod(UserPaymentMethodDto userPaymentMethod) {
        UserPaymentMethod = userPaymentMethod;
    }

    public String getProviderUsername() {
        return providerUsername;
    }

    public void setProviderUsername(String providerUsername) {
        this.providerUsername = providerUsername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrimary_image() {
        return primary_image;
    }

    public void setPrimary_image(String primary_image) {
        this.primary_image = primary_image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }



    public void setPrice(Integer price) {
        this.price = price;
    }
}
