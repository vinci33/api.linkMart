package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;

@Entity
@Table(name = "request")
public class RequestModel {

    @Id
    private String id;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "location_id")
    private int location_id;
    @Column(name = "category_id")
    private int category_id;
    @Column(name = "item")
    private String item;
    @Column(name = "url")
    private String url;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "offer_price")
    private int offer_price;
    @Column(name = "request_remark")
    private String request_remark;
    @Column(name = "created_at", insertable = false, updatable = false)
    @CreationTimestamp
    private String createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    @UpdateTimestamp
    private String updatedAt;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="requestModel", cascade={CascadeType.ALL})
    private List<ImageModel> images;

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void makeRequestCase() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(int offer_price) {
        this.offer_price = offer_price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", created_by='" + created_by + '\'' +
                ", location_id=" + location_id +
                ", category_id=" + category_id +
                ", item='" + item + '\'' +
                ", image='" + images + '\'' +
                ", url='" + url + '\'' +
                ", quantity=" + quantity +
                ", offer_price=" + offer_price +
                ", request_remark='" + request_remark + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
