package dev.park.e.bookcafemanager.repository;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Isbn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IsbnRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    IsbnRepository isbnRepository;

    @Test
    void ISBN_생성_및_조회() {
        //given
        Isbn isbn = ObjectFactory.getIsbnWithoutId(1);
        categoryRepository.save(isbn.getBook().getCategory());
        bookRepository.save(isbn.getBook());

        //when
        isbnRepository.save(isbn);

        //then
        Isbn savedIsbn = isbnRepository.findByIsbn(isbn.getIsbn()).orElseThrow();
        assertThat(savedIsbn).usingRecursiveComparison().ignoringFields("id").isEqualTo(isbn);
        assertThat(isbn.getId()).isNotNull();
    }
}