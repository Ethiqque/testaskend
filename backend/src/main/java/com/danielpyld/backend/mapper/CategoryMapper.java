package com.danielpyld.backend.mapper;

import com.danielpyld.backend.dto.CategoryDto;
import com.danielpyld.backend.dto.FilterDto;
import com.danielpyld.backend.model.Category;
import com.danielpyld.backend.model.Filter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto fromEntityToDto(Category category);

    Category fromDtoToEntity(CategoryDto categoryDto);

    List<CategoryDto> fromEntityToDto(List<Category> categories);
}
