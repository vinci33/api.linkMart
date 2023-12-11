package com.linkmart.service;

import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import com.linkmart.repositories.CategoryFieldOptionRepository;
import com.linkmart.repositories.CategoryFieldRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryFieldRepository categoryFieldRepository;

    @Autowired
    CategoryFieldOptionRepository categoryFieldOptionRepository;

    public List<CategoryFieldModel>  getCategoryFieldOption (Integer categoryId) {
        var categoryFieldIds = categoryFieldRepository.findCategoryFieldIdByCategoryId(categoryId);
        List<CategoryFieldModel>  categoryField = categoryFieldRepository.findCategoryFieldByCategoryId(1);
        logger.info(categoryFieldIds.toString());
        //build categoryField
        var category = new CategoryFieldModel();
        List<CategoryFieldOptionModel> fieldOption = this.categoryFieldOptionRepository.findCategoryFieldOptionByCategoryFieldId(1);
        category.setCategoryFieldOptions(fieldOption);
//        category.setCategoryFieldId(categoryField.getCategoryFieldId());
//        category.setCategoryId(categoryField.getCategoryId());
//        category.setCategoryFieldName(categoryField.getCategoryFieldName());
        return categoryField;
    }
}
