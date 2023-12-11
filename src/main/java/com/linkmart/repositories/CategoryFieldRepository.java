package com.linkmart.repositories;

import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CategoryFieldRepository extends JpaRepository<CategoryFieldModel, Integer> {
    @Query(value = """
           Select id from category_field where category_id =:categoryId
            """, nativeQuery = true)
    List<Integer> findCategoryFieldIdByCategoryId (Integer categoryId);

    @Query(value = """
           Select * from category_field where category_id =:categoryId
            """, nativeQuery = true)
    List<CategoryFieldModel> findCategoryFieldByCategoryId (Integer categoryId);
}
