package com.danielpyld.backend.service;

import com.danielpyld.backend.dto.FilterDto;
import com.danielpyld.backend.mapper.FilterMapper;
import com.danielpyld.backend.model.Category;
import com.danielpyld.backend.model.Criteria;
import com.danielpyld.backend.model.Filter;
import com.danielpyld.backend.repository.CategoryRepository;
import com.danielpyld.backend.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FilterService {

    private final FilterRepository filterRepository;
    private final CategoryRepository categoryRepository;  // Added to fetch Category
    private final FilterMapper filterMapper;

    public List<FilterDto> getAllFilters() {
        return filterMapper.fromEntityToDto(filterRepository.findAll());
    }

    public FilterDto saveFilter(FilterDto filterDto) {
        Filter filter = filterMapper.fromDtoToEntity(filterDto);

        for (Criteria criteria : filter.getCriterias()) {
            // Fetch the existing Category (criteriaType and condition) from the database
            Category criteriaType = categoryRepository.findByCode(criteria.getCriteriaType().getCode());
            Category condition = categoryRepository.findByCode(criteria.getCondition().getCode());

            if (criteriaType == null || condition == null) {
                throw new IllegalArgumentException("Invalid criteriaType or condition");
            }

            criteria.setCriteriaType(criteriaType);  // Ensure CriteriaType is managed
            criteria.setCondition(condition);  // Ensure Condition is managed
            criteria.setFilter(filter);  // Set the parent filter reference
        }

        filter = filterRepository.save(filter);
        return filterMapper.fromEntityToDto(filter);
    }
}
