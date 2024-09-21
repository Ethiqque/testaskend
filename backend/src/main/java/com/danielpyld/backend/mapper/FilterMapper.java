package com.danielpyld.backend.mapper;

import com.danielpyld.backend.dto.FilterDto;
import com.danielpyld.backend.model.Filter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CriteriaMapper.class})
public interface FilterMapper {

    FilterDto fromEntityToDto(Filter filter);

    Filter fromDtoToEntity(FilterDto filterDto);

    // Add the following method to map a list of Filter entities to a list of FilterDto
    List<FilterDto> fromEntityToDto(List<Filter> filters);
}
