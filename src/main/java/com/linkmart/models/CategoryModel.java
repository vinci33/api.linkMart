package com.linkmart.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="category", cascade={CascadeType.ALL})
    private List<RequestModel> requestModel;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RequestModel> getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(List<RequestModel> requestModel) {
        this.requestModel = requestModel;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +

                '}';
    }
}
