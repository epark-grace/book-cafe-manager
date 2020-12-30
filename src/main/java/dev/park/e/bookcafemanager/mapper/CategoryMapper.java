package dev.park.e.bookcafemanager.mapper;

import dev.park.e.bookcafemanager.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> selectCategoryList();
}
