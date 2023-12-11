package com.linkmart.dtos;

public class CategoryFieldOptionDto {
    private String categoryFieldOption;

    public String getCategoryFieldOption() {
        return categoryFieldOption;
    }

    public void setCategoryFieldOption(String categoryFieldOption) {
        this.categoryFieldOption = categoryFieldOption;
    }

    @Override
    public String toString() {
        return "CategoryFieldOptionDto{" +
                "categoryFieldOption=" + categoryFieldOption +
                '}';
    }
}
