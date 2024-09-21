package com.danielpyld.backend.service;

import com.danielpyld.backend.dto.CategoryDto;
import com.danielpyld.backend.mapper.CategoryMapper;
import com.danielpyld.backend.model.Category;
import com.danielpyld.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategoriesByDomainCode(String domainCode) {
        List<Category> categories = categoryRepository.getAllByDomainCode(domainCode);
        return categoryMapper.fromEntityToDto(categories);
    }
}
