package com.myapp.ecommerce.controller;


import com.myapp.ecommerce.dto.CategoryResponseDto;
import com.myapp.ecommerce.entities.Category;
import com.myapp.ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(
            @RequestBody Category category) {

        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "Category deleted successfully";
    }
}




















