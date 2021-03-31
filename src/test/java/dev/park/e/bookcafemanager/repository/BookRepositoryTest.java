package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.ObjectFactory;
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

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 도서_생성_및_목록_조회() {
        //given
        assertThat(bookRepository.count()).isEqualTo(0);

        int count = 20;
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Book book = ObjectFactory.getBookEntityWithoutId(i);
            categoryRepository.save(book.getCategory());
            books.add(book);
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
        Book book = ObjectFactory.getBookEntityWithId(1);
        categoryRepository.save(book.getCategory());
        bookRepository.save(book);

        //when
        Book savedBook = bookRepository.findById(book.getId()).get();

        //then
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void 도서_삭제() {
        //given
        Book book = ObjectFactory.getBookEntityWithoutId(1);
        categoryRepository.save(book.getCategory());
        bookRepository.save(book);

        //when
        bookRepository.deleteById(book.getId());

        //then
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

    @Test
    void 책장번호로_도서_조회() {
        //given
        String shelfName = "1";

        //when
        List<Book> books = bookRepository.findByShelfName(shelfName);

        //then
        assertThat(books).allSatisfy(book -> assertThat(book.getShelfName()).isEqualTo(shelfName));
    }
}