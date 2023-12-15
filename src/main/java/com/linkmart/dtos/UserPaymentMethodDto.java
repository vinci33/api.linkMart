package com.linkmart.dtos;

public class UserPaymentMethodDto {
    private  String payment_method;
    private String card_no;
    private String card_holder_name;
    private String expiry_date;

    public UserPaymentMethodDto(String payment_method, String card_no, String card_holder_name, String expiry_date) {
        this.payment_method = payment_method;
        this.card_no = card_no;
        this.card_holder_name = card_holder_name;
        this.expiry_date = expiry_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
