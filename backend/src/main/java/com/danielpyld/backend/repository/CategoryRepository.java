package com.danielpyld.backend.repository;

import com.danielpyld.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllByDomainCode(String domainCode);
    Category findByCode(String code);  // Add this method to find Category by code

}
