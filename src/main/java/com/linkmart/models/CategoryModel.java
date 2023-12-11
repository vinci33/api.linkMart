package com.linkmart.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;


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


    @Override
    public String toString() {
        return "Category{" +
                "id='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +

                '}';
    }
}
