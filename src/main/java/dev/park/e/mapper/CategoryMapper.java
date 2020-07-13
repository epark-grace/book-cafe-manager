package dev.park.e.mapper;

import dev.park.e.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> selectCategoryList();
}
