package dev.park.e.mapper;

import dev.park.e.dto.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookMapper {
    int selectBookCount(@Param("search") Map<String, String[]> search);

    int insertBook(List<Book> book);

    int deleteBookById(int id);

    Book selectBookById(int id);

    List<Book> selectBookList(@Param("search") Map<String, String[]> search, @Param("rowCount") int rowCount, @Param("rowLimit") int rowLimit);

    int updateBook(Book book);
}
