package com.danielpyld.backend.mapper;

import com.danielpyld.backend.dto.CriteriaDto;
import com.danielpyld.backend.model.Criteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CriteriaMapper {

    @Mapping(target = "filter", ignore = true)  // To avoid circular reference
    CriteriaDto fromEntityToDto(Criteria criteria);

    @Mapping(target = "filter", ignore = true)  // To avoid circular reference
    Criteria fromDtoToEntity(CriteriaDto criteriaDto);
}
