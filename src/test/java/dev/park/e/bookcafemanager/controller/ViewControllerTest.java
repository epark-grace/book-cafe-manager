package dev.park.e.bookcafemanager.controller;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.CategoryDto;
import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.service.BookService;
import dev.park.e.bookcafemanager.service.CategoryService;
import dev.park.e.bookcafemanager.service.PaginationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ViewController.class)
class ViewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    CategoryService categoryService;

    @MockBean
    PaginationService paginationService;

    @Test
    void 인덱스_페이지() throws Exception {
        //given
        String uri = "/";

        //when
        ResultActions resultActions = mockMvc.perform(get(uri));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Hello, world!")))
                .andDo(print());
    }

    @Test
    void 도서목록_페이지() throws Exception {
        //given
        String uri = "/book-list/{page}";
        int page = 1;

        List<Book> bookEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bookEntities.add(ObjectFactory.getBookEntityWithId(i));
        }

        Pagination pagination = new Pagination(30, page);
        List<Category> categories = new ArrayList<>();
        categories.add(ObjectFactory.getCategoryEntityWithId(1));

        given(bookService.getBooks(anyInt())).willReturn(bookEntities);
        given(paginationService.getPagination(anyInt(), any(Search.class))).willReturn(pagination);
        given(categoryService.getCategories()).willReturn(categories);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri, String.valueOf(page)));

        //then
        then(bookService).should(never()).getBooksBySearch(anyInt(), any(Search.class));
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("book-list"))
                .andExpect(model().attribute("bookList", hasSize(bookEntities.size())))
                .andExpect(model().attribute("pagination", pagination))
                .andExpect(model().attribute("categories", hasSize(categories.size())))
                .andDo(print());
    }

    @Test
    void 경로에_페이지가_없으면_1페이지로() throws Exception {
        //given
        String uri = "/book-list";

        //when
        ResultActions resultActions = mockMvc.perform(get(uri));

        //then
        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(uri + "/1"))
                .andDo(print());

    }

    @Test
    void 도서검색_결과_페이지() throws Exception {
        //given
        String uri = "/book-list/{page}";
        int page = 1;
        String criteria = "title";
        String keyword = "test##title";
        Search search = new Search(criteria, keyword);

        List<BookDto.Response> responseDtos = new ArrayList<>();
        Pagination pagination = new Pagination(30, page);
        List<Category> categories = new ArrayList<>();

        given(bookService.getBooksBySearch(anyInt(), any(Search.class))).willReturn(responseDtos);
        given(paginationService.getPagination(anyInt(), any(Search.class))).willReturn(pagination);
        given(categoryService.getCategories()).willReturn(categories);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri, String.valueOf(page)).param("criteria", criteria).param("keyword", keyword));

        //then
        then(bookService).should(never()).getBooks(anyInt());
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("book-list"))
                .andExpect(model().attribute("bookList", responseDtos))
                .andExpect(model().attribute("pagination", pagination))
                .andExpect(model().attribute("categories", CategoryDto.Response.listOf(categories)))
                .andExpect(model().attribute("search", Matchers.any(Search.class)))
                .andExpect(content().string(containsString("?criteria=" + criteria + "&amp;keyword=" + String.join(" ", search.getKeyword()))))
                .andDo(print());
    }

    @Test
    void 책장번호_일괄변경_페이지() throws Exception {
        //given
        String uri = "/shelf-editor";

        //when
        ResultActions resultActions = mockMvc.perform(get(uri));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("shelf-editor"));
    }

    @Test
    void 도서_등록_페이지() throws Exception {
        //given
        String uri = "/book-creation-form";
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            categories.add(ObjectFactory.getCategoryEntityWithId(i));
        }
        given(categoryService.getCategories()).willReturn(categories);

        List<CategoryDto.Response> categoriesDto = CategoryDto.Response.listOf(categories);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri));

        //then
        MvcResult result = resultActions.andExpect(status().isOk())
                .andExpect(view().name("book-creation-form"))
                .andReturn();

        assertThat(result.getModelAndView().getModel().get("categories")).usingRecursiveComparison().isEqualTo(categoriesDto);
    }
}