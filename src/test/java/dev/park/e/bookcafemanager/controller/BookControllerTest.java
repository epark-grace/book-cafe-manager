package dev.park.e.bookcafemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.park.e.bookcafemanager.converter.Converter;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.HttpResponseBody;
import dev.park.e.bookcafemanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    BookService bookService;

    @MockBean
    Converter<Book> bookConverter;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 도서_생성() throws Exception {
        //given
        String uri = "/api/books";
        List<BookDto.Request> requestDtos = new ArrayList<>();
        List<Book> bookEntities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            requestDtos.add(getBookRequestDto(i));
            bookEntities.add(getBookWithCategory(i));
        }

        given(bookConverter.toEntities(requestDtos)).willReturn(bookEntities);

        //when
        ResultActions resultActions = mockMvc.perform(post(uri)
                .content(objectMapper.writeValueAsString(requestDtos))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        HttpResponseBody body = new HttpResponseBody("등록되었습니다.", requestDtos.size());

        resultActions.andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(body)))
                .andDo(print());
    }

    @Test
    void 도서_삭제() throws Exception {
        //given
        String uri = "/api/books/{id}";
        long id = 1;
        given(bookService.deleteBookById(id)).willReturn(id);

        //when
        ResultActions resultActions = mockMvc.perform(delete(uri, id));

        //then
        HttpResponseBody body = new HttpResponseBody("삭제되었습니다.", id);

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(body)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void 도서_조회() throws Exception {
        //given
        String uri = "/api/books/{id}";
        long id = 1;

        Book bookEntity = getBookWithCategory(1);
        given(bookService.getBookById(id)).willReturn(bookEntity);

        //when
        ResultActions resultActions = mockMvc.perform(get(uri, id));

        //then
        BookDto.Response responseDto = new BookDto.Response(bookEntity);
        HttpResponseBody body = new HttpResponseBody("조회되었습니다.", responseDto);

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(body)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void 도서_수정() throws Exception {
        //given
        String uri = "/api/books/{id}";
        long id = 1;
        BookDto.Request requestDto = getBookRequestDto(2);
        Book book = getBookWithCategory(1);
        given(bookService.updateBook(anyLong(), any(BookDto.Request.class))).willReturn(book);

        //when
        ResultActions resultActions = mockMvc.perform(put(uri, id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        //then
        BookDto.Response responseDto = new BookDto.Response(book);
        HttpResponseBody body = new HttpResponseBody("수정되었습니다.", responseDto);

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(body)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void 책장번호_일괄변경() throws Exception {
        //given
        String uri = "/books";
        String existingShelfName = "1";

        //when
        ResultActions resultActions = mockMvc.perform(patch(uri).param("shelf-name", existingShelfName)
                .content("{\"newShelfName\":\"2\"}")
                .contentType("application/json"));

        //then
        then(bookService).should().updateShelfName(existingShelfName, "2");
        resultActions.andExpect(status().isNoContent());
    }

    private BookDto.Request getBookRequestDto(int i) {
        BookDto.Request dto = new BookDto.Request();
        dto.setAuthor("작가" + i);
        dto.setFinished(true);
        dto.setCategoryId((long) i);
        dto.setPublisher("출판사" + i);
        dto.setForAdult(true);
        dto.setRowNumber((short) i);
        dto.setShelfName(String.valueOf(i));
        dto.setVolume((short) i);
        dto.setTitle("제목" + i);
        return dto;
    }

    private Book getBookWithCategory(int i) {
        return Book.builder().author("작가" + i)
                .category(Category.builder().name("카테고리" + i).build())
                .finished(true)
                .forAdult(true)
                .publisher("출판사" + i)
                .volume((short) i)
                .shelfName(String.valueOf(i))
                .rowNumber((short) i)
                .title("제목" + i)
                .build();
    }
}