package com.linkmart.models;

import com.fasterxml.jackson.databind.JsonNode;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "request")
public class RequestModel{

    @Id
    private String id;
    @Column(name = "created_by")
    private String createBy;
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "category_id")
    private int categoryId;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "item_detail", columnDefinition = "jsonb")
    private ItemDetailModel itemDetail;
    @Column(name = "primary_image")
    private String primaryImage;
    @Column(name = "item")
    private String item;
    @Column(name = "url")
    private String url;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "offer_price")
    private int offerPrice;
    @Column(name = "request_remark")
    private String requestRemark;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void makeRequestCase() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public ItemDetailModel getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetailModel itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
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

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", locationId=" + locationId +
                ", categoryId=" + categoryId +
                ", itemDetail=" + itemDetail +
                ", primaryImage='" + primaryImage + '\'' +
                ", item='" + item + '\'' +
                ", url='" + url + '\'' +
                ", quantity=" + quantity +
                ", offerPrice=" + offerPrice +
                ", requestRemark='" + requestRemark + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", images=" + images +
                '}';
    }
}
