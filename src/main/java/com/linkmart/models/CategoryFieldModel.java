package com.linkmart.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_field")
public class CategoryFieldModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryFieldId;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "is_option")
    private Boolean isOption;

    @Column(name = "category_field_name")
    private String categoryFieldName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="categoryFieldModel", cascade={CascadeType.ALL})
    private List<CategoryFieldOptionModel> categoryFieldOptions;

    public void setCategoryFieldOptions(List<CategoryFieldOptionModel> categoryFieldOptions) {
        this.categoryFieldOptions = categoryFieldOptions;
    }

    public List<CategoryFieldOptionModel> getCategoryFieldOptions() {
        return categoryFieldOptions;
    }

    public int getCategoryFieldId() {
        return categoryFieldId;
    }

    public void setCategoryFieldId(int categoryFieldId) {
        this.categoryFieldId = categoryFieldId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getOption() {
        return isOption;
    }

    public void setOption(Boolean isOption) {
        this.isOption = isOption;
    }

    public String getCategoryFieldName() {
        return categoryFieldName;
    }

    public void setCategoryFieldName(String categoryFieldName) {
        this.categoryFieldName = categoryFieldName;
    }



    @Override
    public String toString() {
        return "CategoryFieldModel{" +
                "categoryFieldId=" + categoryFieldId +
                ", categoryId=" + categoryId +
                ", isOption=" + isOption +
                ", categoryFieldName='" + categoryFieldName + '\'' +
//                ", categoryFieldOptions=" + categoryFieldOptions +
                '}';
    }
}
