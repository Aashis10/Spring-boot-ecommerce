package com.myapp.ecommerce.service;

import com.myapp.ecommerce.dto.CategoryResponseDto;
import com.myapp.ecommerce.entities.Category;
import com.myapp.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CategoryService {

    private static final Logger log =
            LoggerFactory.getLogger(CategoryService.class);


    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
     List<CategoryResponseDto> ctr =   categories.stream()
                .map((n)->new CategoryResponseDto(n.getId(),n.getName()))
                .toList();

            return ctr;

    }

    public Category createCategory(
            Category category) {

        log.debug("Creating category {}", category.getName());

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"));
    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found with id: " + id));


        categoryRepository.delete(category);
    }
    }



