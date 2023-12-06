package com.linkmart.forms;

import com.linkmart.models.Request;
import org.springframework.web.multipart.MultipartFile;

public class RequestForm {
    private String created_by;
    private int location_id;
    private int category_id;
    private String item;
    private MultipartFile image;
    private String url;
    private int quantity;
    private String request_remark;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRequest_remark() {
        return request_remark;
    }

    public void setRequest_remark(String request_remark) {
        this.request_remark = request_remark;
    }

    public Request postRequest(){
        var request = new Request();
        request.setCreated_by(this.created_by);
        request.setLocation_id(this.location_id);
        request.setCategory_id(this.category_id);
        request.setItem(this.item);
        request.setUrl(this.url);
        request.setQuantity(this.quantity);
        request.setRequest_remark(this.request_remark);
        return request;
    }
}
