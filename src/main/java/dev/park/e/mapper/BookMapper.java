package dev.park.e.mapper;

import dev.park.e.dto.Book;

import java.util.List;

public interface BookMapper {
    int insertBook(List<Book> book);
}
