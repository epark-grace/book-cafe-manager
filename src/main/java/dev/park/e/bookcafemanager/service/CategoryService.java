package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.CategoryDto;
import dev.park.e.bookcafemanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("CategoryService.getCategory(" + id + "): 존재하지않는 Category ID"));
    }
}
