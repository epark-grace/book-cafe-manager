package dev.park.e.bookcafemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.CategoryDto;
import dev.park.e.bookcafemanager.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 카테고리_조회() throws Exception {
        //given
        String uri = "/api/categories";
        List<Category> categories = new ArrayList<>();

        given(categoryService.getCategories()).willReturn(categories);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(CategoryDto.Response.listOf(categories))))
                .andDo(print());
    }
}