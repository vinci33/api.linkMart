package com.linkmart.repositories;

import com.linkmart.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer> {

}
