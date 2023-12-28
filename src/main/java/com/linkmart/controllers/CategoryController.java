package com.linkmart.controllers;

import com.linkmart.dtos.CategoryDto;
import com.linkmart.dtos.CategoryFieldDto;
import com.linkmart.mappers.CategoryFieldMapper;
import com.linkmart.mappers.CategoryMapper;
import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import com.linkmart.repositories.CategoryFieldOptionRepository;
import com.linkmart.repositories.CategoryRepository;
import com.linkmart.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category", description = """
        ***All Category APIs***
        - Represents the CategoryController class, which is responsible for handling HTTP requests related to the Category entity.
        
        - It retrieves a list of all categories from the database and maps them to DTO (Data Transfer Object) objects for use by the Offer and request entities.
          
        """)
@RestController
@RequestMapping(value = "")
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final CategoryRepository categoryRepository;


    private final CategoryService categoryService;


    private final CategoryFieldOptionRepository categoryFieldOptionRepository;

    public CategoryController (CategoryRepository categoryRepository, CategoryService categoryService, CategoryFieldOptionRepository categoryFieldOptionRepository){
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.categoryFieldOptionRepository = categoryFieldOptionRepository;
    };

    @Operation(summary = "Get all category",
            description = "Retrieves a list of all categories and maps them to CategoryDto .",
            tags = { "Category","Get" })
    @GetMapping(value = "/category")
    public List<CategoryDto> getAllCategory (){
        var result = this.categoryRepository.findAll();
        return CategoryMapper.INSTANCE.getAllCategory(result);
    }

    @Operation(summary = "Get all category field",
            description = "Retrieves a list of all category fields with categoryId and maps them to CategoryFieldDto .",
            tags = { "Category","Get" })
    @GetMapping(value = "/category/{categoryId}")
    public List<CategoryFieldDto>  getCategoryField(@PathVariable int categoryId) {
        List<CategoryFieldModel> categoryField = this.categoryService.getCategoryFieldOption(categoryId);
        return CategoryFieldMapper.INSTANCE.getAllCategoryField(categoryField);
    }

    @Operation(summary = "Get all category field option",
            description = "Retrieves a list of all category field options with categoryFieldId and maps them to CategoryFieldOptionModel .",
            tags = { "Category","Get" })
    @GetMapping(value = "/category/field/{categoryFieldId}")
    public List<CategoryFieldOptionModel> getCategoryField(@PathVariable Integer categoryFieldId) {
        return categoryFieldOptionRepository.findCategoryFieldOptionByCategoryFieldId(categoryFieldId);
    }
}
