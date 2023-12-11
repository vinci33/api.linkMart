package com.linkmart.controllers;

import com.linkmart.dtos.CategoryDto;
import com.linkmart.mappers.CategoryMapper;
import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import com.linkmart.repositories.CategoryFieldOptionRepository;
import com.linkmart.repositories.CategoryRepository;
import com.linkmart.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryFieldOptionRepository categoryFieldOptionRepository;

    @GetMapping(value = "/category")
    public List<CategoryDto> getAllCategory (){
        var result = this.categoryRepository.findAll();
        return CategoryMapper.INSTANCE.getAllCategory(result);
    }

    @GetMapping(value = "/category/{categoryId}")
    public List<CategoryFieldModel>  getCategoryField(@PathVariable int categoryId) {
        var categoryIdList = this.categoryService.getCategoryFieldOption(categoryId);
        return categoryIdList;
    }

    @GetMapping(value = "/category/field/{categoryFieldId}")
    public List<CategoryFieldOptionModel> getCategoryField(@PathVariable Integer categoryFieldId) {
        return categoryFieldOptionRepository.findCategoryFieldOptionByCategoryFieldId(categoryFieldId);
    }
}
