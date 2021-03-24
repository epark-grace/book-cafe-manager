package dev.park.e.bookcafemanager.converter;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.Convertible;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.LongFunction;

@RequiredArgsConstructor
public class BookConverter implements Converter<Book> {

    private final LongFunction<Optional<Category>> bookInjector;

    @Override
    public Book toEntity(Convertible dto) {
        Book.BookBuilder builder = Book.builder();
        try {
            for (Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(dto);
                builder = switch (fieldName) {
                    case "title" -> builder.title((String) fieldValue);
                    case "author" -> builder.author((String) fieldValue);
                    case "publisher" -> builder.publisher((String) fieldValue);
                    case "volume" -> builder.volume((Short) fieldValue);
                    case "shelfName" -> builder.shelfName((String) fieldValue);
                    case "rowNumber" -> builder.rowNumber((Short) fieldValue);
                    case "finished" -> builder.finished((Boolean) fieldValue);
                    case "forAdult" -> builder.forAdult((Boolean) fieldValue);
                    case "categoryId" -> builder.category(bookInjector.apply((Long) fieldValue).orElseThrow());
                    default -> throw new IllegalStateException("Unexpected value: " + fieldName);
                };
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return builder.build();
    }
}
