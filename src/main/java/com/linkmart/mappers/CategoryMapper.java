package com.linkmart.mappers;

import com.linkmart.dtos.CategoryDto;
import com.linkmart.models.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.swing.*;
import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDto> getAllCategory (List<CategoryModel> category);
}
