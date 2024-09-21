package com.danielpyld.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaDto {

    private Long id;
    private FilterDto filter;
    private CategoryDto criteriaType;
    private CategoryDto condition;
    private String value;
    private Boolean isSelected;
}
