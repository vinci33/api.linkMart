package com.linkmart.forms;

import com.linkmart.models.ImageModel;
import com.linkmart.utils.UtilMethod;

import java.util.List;
import java.util.Map;

public class RequestForm {
    private String createdBy;
    private int locationId;
    private int categoryId;
    private String itemDetail;
    private String item;
    private List<ImageModel> image;
    private String url;
    private int quantity;
    private int offerPrice;
    private String requestRemark;
    private UtilMethod utilMethod = new UtilMethod();

    public <T> void processFormDataString(Map<String,T> formData, T defaultValue){
        Map<String,T> processedFormData = utilMethod.formDataProcessor(formData,new String[]{"itemDetail","item","url","requestRemark"}, defaultValue);

        if (processedFormData.get("itemDetail") instanceof String) {
            this.itemDetail = (String) processedFormData.get("itemDetail");
        }
        if (processedFormData.get("item") instanceof String) {
            this.item = (String) processedFormData.get("item");
        }
        if (processedFormData.get("url") instanceof String) {
            this.url = (String) processedFormData.get("url");
        }
        if (processedFormData.get("requestRemark") instanceof String) {
            this.requestRemark = (String) processedFormData.get("requestRemark");
        }

    }
    public <T> void processFormDataInteger(Map<String,T> formData, T defaultValue){
        Map<String,T> processedFormData = utilMethod.formDataProcessor(formData,new String[]{"quantity","offerPrice"}, defaultValue);

        if (processedFormData.get("quantity") instanceof Integer) {
            this.quantity = (Integer) processedFormData.get("quantity");
        }
        if (processedFormData.get("offerPrice") instanceof Integer) {
            this.offerPrice = (Integer) processedFormData.get("offerPrice");
        }


    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<ImageModel> getImage() {
        return image;
    }

    public void setImage(List<ImageModel> image) {
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

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getRequestRemark() {
        return requestRemark;
    }

    public void setRequestRemark(String requestRemark) {
        this.requestRemark = requestRemark;
    }



}
