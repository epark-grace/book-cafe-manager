package dev.park.e.service;

import dev.park.e.dto.Category;
import dev.park.e.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getCategories() {
        return categoryMapper.selectCategoryList();
    }
}
