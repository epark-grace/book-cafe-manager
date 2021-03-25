package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.repository.BookMapper;
import dev.park.e.bookcafemanager.repository.BookRepository;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
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
        bookMocks.add(getBookWithCategory(1));

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
        booksMock.add(new BookDto.Response(getBookWithCategory(1)));

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
        Book book = getBookWithCategory(1);
        given(bookRepository.findById(anyLong())).willReturn(Optional.ofNullable(book));
        Category category = Category.builder().name("다른카테고리").build();
        given(categoryService.getCategory(anyLong())).willReturn(category);

        BookDto.Request dto = new BookDto.Request();
        dto.setCategoryId(2L);
        dto.setFinished(false);
        dto.setForAdult(false);
        dto.setPublisher("다른출판사");
        dto.setVolume((short) 2);
        dto.setShelfName("2");
        dto.setRowNumber((short) 2);
        dto.setTitle("다른제목");

        //when
        bookService.updateBook(id, dto);

        //then
        assertThat(book).usingRecursiveComparison(RecursiveComparisonConfiguration.builder().build()).ignoringFields("id", "category").isEqualTo(dto);
    }

    @Test
    void 책장번호_일괄변경() {
        //given
        String existingShelfName = "1";
        String newShelfName = "2";
        List<Book> bookMocks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            bookMocks.add(getBookWithCategory(1));
        }

        given(bookRepository.findByShelfName(existingShelfName)).willReturn(bookMocks);

        //when
        bookService.updateShelfName(existingShelfName, newShelfName);

        //then
        assertThat(bookMocks).allSatisfy(book -> assertThat(book.getShelfName()).isEqualTo("2"));
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