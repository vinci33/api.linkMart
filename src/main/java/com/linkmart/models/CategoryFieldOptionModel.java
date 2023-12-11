package com.linkmart.models;

import jakarta.persistence.*;

@Entity
@Table(name = "category_field_option")
public class CategoryFieldOptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer categoryFieldOptionId;

    @Column(name = "category_field_id")
    private Integer categoryFieldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_field_id", updatable = false, insertable = false)
    private CategoryFieldModel categoryFieldModel;

    @Column(name = "category_field_option_name")
    private String categoryFieldOption;

    public int getCategoryFieldOptionId() {
        return categoryFieldOptionId;
    }

    public void setCategoryFieldOptionId(int categoryFieldOptionId) {
        this.categoryFieldOptionId = categoryFieldOptionId;
    }

    public int getCategoryFieldId() {
        return categoryFieldId;
    }

    public void setCategoryFieldId(int categoryFieldId) {
        this.categoryFieldId = categoryFieldId;
    }


    public String getCategoryFieldOption() {
        return categoryFieldOption;
    }

    public void setCategoryFieldOption(String categoryFieldOption) {
        this.categoryFieldOption = categoryFieldOption;
    }

    @Override
    public String toString() {
        return "CategoryFieldOptionModel{" +
                "categoryFieldOptionId=" + categoryFieldOptionId +
                ", categoryFieldId=" + categoryFieldId +
                ", categoryFieldOption='" + categoryFieldOption + '\'' +
                '}';
    }
}
