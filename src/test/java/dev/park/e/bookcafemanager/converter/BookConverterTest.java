package dev.park.e.bookcafemanager.converter;

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
        BookDto.Request dto = getBookRequestDto();
        Category category = Category.builder().name("카테고리").build();
        given(bookInjector.apply(anyLong())).willReturn(Optional.ofNullable(category));

        //when
        Book book = (Book) converter.toEntity(dto);

        //then
        assertThat(book).usingRecursiveComparison().ignoringFields("id", "category").isEqualTo(dto);
        assertThat(book.getCategory().getName()).isEqualTo(category.getName());
    }

    private BookDto.Request getBookRequestDto() {
        BookDto.Request dto = new BookDto.Request();
        dto.setAuthor("작가");
        dto.setFinished(true);
        dto.setCategoryId(1L);
        dto.setPublisher("출판사");
        dto.setForAdult(true);
        dto.setRowNumber((short) 1);
        dto.setShelfName("1");
        dto.setVolume((short) 1);
        dto.setTitle("제목");
        return dto;
    }
}