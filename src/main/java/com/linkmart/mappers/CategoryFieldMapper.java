package com.linkmart.mappers;

import com.linkmart.dtos.CategoryFieldDto;
import com.linkmart.dtos.CategoryFieldOptionDto;
import com.linkmart.models.CategoryFieldModel;
import com.linkmart.models.CategoryFieldOptionModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryFieldMapper {
    CategoryFieldMapper INSTANCE = Mappers.getMapper(CategoryFieldMapper.class);

    List<CategoryFieldOptionDto> getAllCategoryFieldOption (List<CategoryFieldOptionModel> categoryFieldId);

    List<CategoryFieldDto> getAllCategoryField (List<CategoryFieldModel> categoryId);
}
