package com.linkmart.service;

import com.linkmart.dtos.CategoryFieldOptionDto;
import com.linkmart.mappers.CategoryFieldMapper;
import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import com.linkmart.models.ImageModel;
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
        List<CategoryFieldModel> categoryField = categoryFieldRepository.findCategoryFieldByCategoryId(categoryId);
        return categoryField;
    }
}
