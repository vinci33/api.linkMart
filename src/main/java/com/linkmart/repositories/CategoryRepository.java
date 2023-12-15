package com.linkmart.repositories;

import com.linkmart.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer> {

    @Query(value = """
            Select category_name from category where category.id = :category_id
             """, nativeQuery = true)
    String findCategoryNameByCategoryId(@Param("category_id") Integer categoryId);
}
