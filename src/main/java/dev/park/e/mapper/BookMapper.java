package dev.park.e.mapper;

import dev.park.e.dto.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BookMapper {
    int selectBookCount(@Param("search") HashMap<String, Object> search);
    int insertBook(List<Book> book);
    int deleteBookById(int id);
    Book selectBookById(int id);
    List<Book> selectBookList(@Param("rowCount")int rowCount, @Param("rowLimit")int rowLimit);
    int updateBook(Book book);
    List<Book> selectBookListByKeyword(@Param("column") String column, @Param("words") String[] words, @Param("rowCount") int rowCount, @Param("rowLimit") int rowLimit);
}
