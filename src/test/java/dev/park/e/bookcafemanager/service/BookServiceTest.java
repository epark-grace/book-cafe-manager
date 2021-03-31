package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.repository.BookMapper;
import dev.park.e.bookcafemanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookMapper bookMapper;

    @Mock
    BookRepository bookRepository;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    BookService bookService;

    @Test
    void 도서목록_조회() {
        //given
        int currentPage = 1;

        List<Book> bookMocks = new ArrayList<>();
        bookMocks.add(ObjectFactory.getBookEntityWithId(1));

        given(bookRepository.findAll(anyLong(), anyInt())).willReturn(bookMocks);

        //when
        List<Book> books = bookService.getBooks(currentPage);

        //then
        assertThat(books).usingRecursiveComparison().isEqualTo(bookMocks);
    }

    @Test
    void 도서_검색() {
        //given
        int currentPage = 1;
        Search search = new Search("title", "제목");

        List<BookDto.Response> booksMock = new ArrayList<>();
        booksMock.add(new BookDto.Response(ObjectFactory.getBookEntityWithId(1)));

        given(bookMapper.findAllBy(anyLong(), anyInt(), any(Search.class))).willReturn(booksMock);

        //when
        List<BookDto.Response> books = bookService.getBooksBySearch(currentPage, search);

        //then
        assertThat(books).isEqualTo(booksMock);
    }

    @Test
    void 도서_수정() {
        //given
        long id = 1;
        Book book = ObjectFactory.getBookEntityWithId(1);
        given(bookRepository.findById(1L)).willReturn(Optional.ofNullable(book));

        Category category = ObjectFactory.getCategoryEntityWithId(2);
        given(categoryService.getCategory(2L)).willReturn(category);

        BookDto.Request dto = ObjectFactory.getBookRequestDto(2);

        //when
        bookService.updateBook(id, dto);

        //then
        assertThat(book).usingRecursiveComparison().ignoringFields("id", "isbn", "category").isEqualTo(dto);
    }

    @Test
    void 책장번호_일괄변경(){
        //given
        String existingShelfName = "1";
        String newShelfName = "2";
        List<Book> bookMocks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            bookMocks.add(ObjectFactory.getBookEntityWithId(i));
        }

        given(bookRepository.findByShelfName(existingShelfName)).willReturn(bookMocks);

        //when
        bookService.updateShelfName(existingShelfName, newShelfName);

        //then
        assertThat(bookMocks).allSatisfy(book -> assertThat(book.getShelfName()).isEqualTo("2"));
    }
}