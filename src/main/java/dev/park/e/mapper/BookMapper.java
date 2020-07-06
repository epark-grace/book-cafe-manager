package dev.park.e.mapper;

import dev.park.e.dto.Book;

import java.util.List;

public interface BookMapper {
    int selectBookCount();
    int insertBook(List<Book> book);
    int deleteBookByid(int id);
}
