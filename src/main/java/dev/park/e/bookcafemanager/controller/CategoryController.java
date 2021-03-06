package dev.park.e.bookcafemanager.controller;

import dev.park.e.bookcafemanager.dto.CategoryDto;
import dev.park.e.bookcafemanager.service.CategoryService;
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
    public List<CategoryDto.Response> getCategories() {
        return CategoryDto.Response.listOf(categoryService.getCategories());
    }
}
