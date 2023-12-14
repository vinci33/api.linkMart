package com.linkmart.dtos;

import com.google.gson.Gson;
import com.linkmart.models.ImageModel;
import com.linkmart.models.ItemDetailModel;

import java.util.List;

public class OneRequestDtoImpl implements OneRequestDto {
        private String requestId;
        private int locationId;
        private String locationName;
        private int categoryId;
        private String categoryName;
        private String itemDetail;

        // Constructor
        public OneRequestDtoImpl(String requestId, int locationId, String locationName, int categoryId, String categoryName, String itemDetail) {
            this.requestId = requestId;
            this.locationId = locationId;
            this.locationName = locationName;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.itemDetail = itemDetail;
        }

        // Getter and Setter methods (implementing the interface)
        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getItemDetail() {
            return itemDetail;
        }

    @Override
    public String getItem() {
        return null;
    }

    @Override
    public String getPrimaryImage() {
        return null;
    }

    @Override
    public List<ImageModel> getImages() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public int getQuantity() {
        return 0;
    }

    @Override
    public String getRequestRemark() {
        return null;
    }

    @Override
    public int getOfferPrice() {
        return 0;
    }

    @Override
    public String getCreatedBy() {
        return null;
    }

    @Override
    public String getCreatedAt() {
        return null;
    }

    @Override
    public String getUpdatedAt() {
        return null;
    }

    public void setItemDetail(String itemDetail) {
            this.itemDetail = itemDetail;
        }

        // Convenience method to parse itemDetail JSON string using Gson
        public ItemDetailModel parseItemDetail() {
            Gson gson = new Gson();
            return gson.fromJson(itemDetail, ItemDetailModel.class);
        }
    }

