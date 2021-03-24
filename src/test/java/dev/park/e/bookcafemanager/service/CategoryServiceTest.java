package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void 카테고리_목록_조회() {
        //given
        int count = 10;
        List<Category> categoriesMock = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            categoriesMock.add(getCategory(i));
        }
        given(categoryRepository.findAll()).willReturn(categoriesMock);

        //when
        List<Category> categories = categoryService.getCategories();

        //then
        assertThat(categories).hasSize(count)
                .usingRecursiveComparison()
                .isEqualTo(categoriesMock);
    }

    @Test
    void 단일카테고리_조회() {
        //given
        Category categoryMock = getCategory(1);
        given(categoryRepository.findById(anyLong())).willReturn(Optional.ofNullable(categoryMock));

        //when
        Category category = categoryService.getCategory(1);

        //then
        assertThat(category).isEqualTo(categoryMock);
    }

    private Category getCategory(int index) {
        return Category.builder().name("카테고리" + index).build();
    }
}