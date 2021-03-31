package dev.park.e.bookcafemanager.converter;

import dev.park.e.bookcafemanager.ObjectFactory;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.function.LongFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookConverterTest {

    @Mock
    LongFunction<Optional<Category>> bookInjector;

    @InjectMocks
    BookConverter converter;

    @Test
    void 엔티티_생성() {
        //given
        BookDto.Request dto = ObjectFactory.getBookRequestDto(1);
        Category category = ObjectFactory.getCategoryEntityWithId(1);
        given(bookInjector.apply(1L)).willReturn(Optional.ofNullable(category));

        //when
        Book book = converter.toEntity(dto);

        //then
        assertThat(book).usingRecursiveComparison().ignoringFields("id", "isbn", "category").isEqualTo(dto);
        assertThat(book.getCategory().getName()).isEqualTo(category.getName());
    }
}