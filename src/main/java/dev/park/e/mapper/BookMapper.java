package dev.park.e.mapper;

import dev.park.e.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int selectBookCount();
    int insertBook(List<Book> book);
    int deleteBookById(int id);
    Book selectBookById(int id);
}
