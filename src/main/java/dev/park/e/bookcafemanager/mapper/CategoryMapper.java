package dev.park.e.bookcafemanager.mapper;

import dev.park.e.bookcafemanager.dto.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryList();
}
