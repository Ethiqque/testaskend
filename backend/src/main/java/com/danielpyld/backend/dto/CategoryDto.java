package com.danielpyld.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String domainCode;
    private String code;
    private String parentCode;
    private List<CategoryDto> subCategories;
    private CategoryValueDto categoryValue;
}
