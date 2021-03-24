package dev.park.e.bookcafemanager.config;

import dev.park.e.bookcafemanager.converter.BookConverter;
import dev.park.e.bookcafemanager.converter.Converter;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.repository.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConverterConfiguration {
    @Bean
    public Converter<Book> bookConverter(CategoryRepository categoryRepository) {
        return new BookConverter(categoryRepository::findById);
    }
}
