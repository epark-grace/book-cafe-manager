package dev.park.e.dao;

import dev.park.e.dto.Book;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    SqlSession sqlSession;

    public BookDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertBook(List<Book> books) {
        return sqlSession.insert("dev.park.e.mapper.BookMapper.insertBook", books);
    }

    public int deleteBookById(int id) {
        return sqlSession.delete("dev.park.e.mapper.BookMapper.deleteBookById", id);
    }
}
