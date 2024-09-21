package com.danielpyld.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDto {

    private Long id;
    private String name;
    private List<CriteriaDto> criterias;
}
