package com.linkmart.services;

import com.linkmart.models.CategoryFieldModel;
import com.linkmart.repositories.CategoryFieldOptionRepository;
import com.linkmart.repositories.CategoryFieldRepository;
import com.linkmart.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());





    private final CategoryFieldRepository categoryFieldRepository;




    public CategoryService(CategoryFieldRepository categoryFieldRepository) {
        this.categoryFieldRepository = categoryFieldRepository;
    }


    //route: /category/{categoryId}
    public List<CategoryFieldModel>  getCategoryFieldOption (Integer categoryId) {
        return  categoryFieldRepository.findCategoryFieldByCategoryId(categoryId);

    }
}
