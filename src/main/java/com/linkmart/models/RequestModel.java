package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "request")
public class RequestModel{
    @Id
    @Column(name = "id")
    private String requestId;

    @Column(name = "created_by")
    private String createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", updatable = false, insertable = false)
    private User users;

    @Column(name = "location_id")
    private Integer locationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id", updatable = false, insertable = false)
    private Location location;

    @Column(name = "category_id")
    private int categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", updatable = false, insertable = false)
    private CategoryModel category;

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void makeRequestCase() {
        ULID ulid = new ULID();
        this.requestId = ulid.nextULID();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "requestId='" + requestId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", users=" + users +
                ", locationId=" + locationId +
                ", location=" + location +
                ", categoryId=" + categoryId +
                ", category=" + category +
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
