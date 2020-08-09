package dev.park.e.controller;

import dev.park.e.dto.Category;
import dev.park.e.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
}
