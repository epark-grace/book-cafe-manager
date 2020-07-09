package dev.park.e.service;

import dev.park.e.dto.Book;
import dev.park.e.dto.Pagination;
import dev.park.e.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Pagination getPagination(int currentPage) {
        return new Pagination(bookMapper.selectBookCount(), currentPage);
    }

    @Transactional
    public List<Book> addBooks(List<Book> books) {
        bookMapper.insertBook(books);
        return books;
    }

    public int removeBook(int id) {
        return bookMapper.deleteBookById(id);
    }
}
