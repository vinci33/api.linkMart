package com.linkmart.repositories;

import com.linkmart.models.CategoryFieldOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryFieldOptionRepository extends JpaRepository<CategoryFieldOptionModel, Integer> {
    List<CategoryFieldOptionModel> findCategoryFieldOptionByCategoryFieldId (Integer categoryFieldId);
}
