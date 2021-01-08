package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.Category;
import dev.park.e.bookcafemanager.repository.CategoryMapper;
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
