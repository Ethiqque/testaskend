package com.danielpyld.backend.controller;

import com.danielpyld.backend.dto.CategoryDto;
import com.danielpyld.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{domainCode}")
    public List<CategoryDto> getAllCategoriesByDomainCode(@PathVariable String domainCode) {
        return categoryService.getAllCategoriesByDomainCode(domainCode);
    }
}
