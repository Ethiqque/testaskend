package com.danielpyld.backend.controller;

import com.danielpyld.backend.dto.FilterDto;
import com.danielpyld.backend.service.FilterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @PostMapping("/save")
    public FilterDto saveFilter(@RequestBody @Valid FilterDto dto) {
        return filterService.saveFilter(dto);
    }

    @GetMapping("/filters")
    public List<FilterDto> getFilters() {
        return filterService.getAllFilters();
    }
}
