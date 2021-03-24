package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 카테고리_생성_및_조회() {
        //given
        assertThat(categoryRepository.count()).isEqualTo(0);
        String name = "name";
        Category category = Category.builder().name(name).build();

        //when
        categoryRepository.save(category);

        //then
        assertThat(categoryRepository.count()).isEqualTo(1);

        List<Category> categories = categoryRepository.findAll();
        assertThat(categories.get(0).getName()).isEqualTo(name);
    }

    @Test
    void 카테고리_ID로_검색() {
        //given
        Category category = Category.builder().name("카테고리").build();
        categoryRepository.save(category);

        //when
        Category savedCategory = categoryRepository.findById(category.getId()).orElseThrow();

        //then
        assertThat(savedCategory).usingRecursiveComparison().isEqualTo(category);
    }
}