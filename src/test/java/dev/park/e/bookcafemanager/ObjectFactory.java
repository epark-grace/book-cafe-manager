package dev.park.e.bookcafemanager;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.domain.Isbn;
import dev.park.e.bookcafemanager.dto.BookDto;

import java.lang.reflect.Field;

public class ObjectFactory {

    public static Object getEntityWithId(Object entity, Long id) {
        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public static Category getCategoryEntityWithoutId(long i) {
        return Category.builder().name("카테고리" + i).build();
    }

    public static Category getCategoryEntityWithId(long i) {
        Category category = getCategoryEntityWithoutId(i);
        return (Category) getEntityWithId(category, i);
    }

    public static Book getBookEntityWithoutId(long i) {
        return Book.builder().author("작가" + i)
                .category(getCategoryEntityWithoutId(i))
                .finished(true)
                .forAdult(true)
                .publisher("출판사" + i)
                .volume((short) i)
                .shelfName(String.valueOf(i))
                .rowNumber((short) i)
                .title("제목" + i)
                .build();
    }

    public static Book getBookEntityWithId(long i) {
        Book book = getBookEntityWithoutId(i);
        return (Book) getEntityWithId(book, i);
    }

    public static Isbn getIsbnWithoutId(long i) {
        return Isbn.builder().isbn(String.format("%013d", i))
                .book(getBookEntityWithoutId(i))
                .build();
    }

    public static Isbn getIsbnWithId(long i) {
        Isbn isbn = getIsbnWithoutId(i);
        return (Isbn) getEntityWithId(isbn, i);
    }

    public static BookDto.Request getBookRequestDto(long i) {
        BookDto.Request dto = new BookDto.Request();
        dto.setAuthor("작가" + i);
        dto.setFinished(true);
        dto.setCategoryId(i);
        dto.setPublisher("출판사" + i);
        dto.setForAdult(true);
        dto.setRowNumber((short) i);
        dto.setShelfName(String.valueOf(i));
        dto.setVolume((short) i);
        dto.setTitle("제목" + i);
        return dto;
    }
}
