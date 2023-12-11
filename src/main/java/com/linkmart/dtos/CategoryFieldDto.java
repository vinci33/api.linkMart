package com.linkmart.dtos;

import java.util.ArrayList;
import java.util.List;

public class CategoryFieldDto {
    private int categoryFieldId;
    private String categoryFieldName;
    private List<CategoryFieldOptionDto> categoryFieldOptions;

    public int getCategoryFieldId() {
        return categoryFieldId;
    }

    public void setCategoryFieldId(int categoryFieldId) {
        this.categoryFieldId = categoryFieldId;
    }

    public String getCategoryFieldName() {
        return categoryFieldName;
    }

    public void setCategoryFieldName(String categoryFieldName) {
        this.categoryFieldName = categoryFieldName;
    }


    public List<String> getCategoryFieldOptions() {
        var result = new ArrayList<String>();
       for (int i=0; i<categoryFieldOptions.size(); i++) {
           result.add(categoryFieldOptions.get(i).getCategoryFieldOption());
       }
       return result;
    }

    public void setCategoryFieldOptions(List<CategoryFieldOptionDto> categoryFieldOptions) {
        this.categoryFieldOptions = categoryFieldOptions;
    }

    @Override
    public String toString() {
        return "CategoryFieldDto{" +
                "categoryFieldId=" + categoryFieldId +
                ", categoryFieldName='" + categoryFieldName + '\'' +
                ", categoryFieldOption=" + categoryFieldOptions +
                '}';
    }
}
