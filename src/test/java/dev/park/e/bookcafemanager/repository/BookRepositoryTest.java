package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.Pagination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    //TODO CategoryRepository 의존성 문제 해결
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 도서_생성_및_목록_조회() {
        //given
        assertThat(bookRepository.count()).isEqualTo(0);

        int count = 20;
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            books.add(getBookWithCategory(i));
        }

        //When
        bookRepository.saveAll(books);

        //Then
        List<Book> savedBooks = bookRepository.findAll(0, Pagination.ROW_LIMIT);
        Collections.reverse(books);

        assertThat(bookRepository.count()).isEqualTo(count);
        assertThat(savedBooks)
                .hasSize(Pagination.ROW_LIMIT)
                .usingRecursiveComparison()
                .isEqualTo(books.subList(0, 10));
    }

    @Test
    void 단일도서_조회() {
        //given
        Book book = getBookWithCategory(1);
        bookRepository.save(book);

        //when
        Book savedBook = bookRepository.findById(book.getId()).get();

        //then
        assertThat(savedBook).isEqualTo(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void 도서_삭제() {
        //given
        Book book = getBookWithCategory(1);
        bookRepository.save(book);

        //when
        bookRepository.deleteById(book.getId());

        //then
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

    private Book getBookWithCategory(int index) {
        Category category = Category.builder().name("카테고리" + index).build();
        categoryRepository.save(category);
        return Book.builder().author("작가" + index)
                .category(category)
                .finished(true)
                .forAdult(true)
                .publisher("출판사" + index)
                .volume((short) index)
                .shelfName(Integer.toString(index))
                .rowNumber((short) index)
                .title("제목" + index)
                .build();
    }
}