package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.dto.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryList();
}
