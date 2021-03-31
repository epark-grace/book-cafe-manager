package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Isbn;
import dev.park.e.bookcafemanager.repository.IsbnRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@ExtendWith(MockitoExtension.class)
class IsbnServiceTest {

    @Mock
    IsbnRepository isbnRepository;

    @Spy
    @InjectMocks
    IsbnService isbnService;

    @Test
    void isbn_조회() {
        //given
        Isbn isbnMock = ObjectFactory.getIsbnWithId(1);
        given(isbnRepository.findByIsbn("0000000000001")).willReturn(java.util.Optional.of(isbnMock));

        //when
        Isbn isbn = isbnService.getIsbn("0000000000001");

        //then
        assertThat(isbn).usingRecursiveComparison().isEqualTo(isbnMock);
    }

    @Test
    void isbn으로_도서_조회() {
        //given
        Isbn isbnMock = ObjectFactory.getIsbnWithId(1);
        willReturn(isbnMock).given(isbnService).getIsbn("0000000000001");

        //when
        Book book = isbnService.getBookByIsbn("0000000000001");

        //then
        assertThat(book).isEqualTo(isbnMock.getBook());
    }
}