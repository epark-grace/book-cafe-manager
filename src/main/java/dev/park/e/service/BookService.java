package dev.park.e.service;

import dev.park.e.dao.BookDao;
import dev.park.e.dto.Book;
import dev.park.e.dto.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Pagination getPagination(int currentPage) {
        return new Pagination(bookDao.selectBookCount(), currentPage);
    }

    @Transactional
    public int addBooks(List<Book> books) {
        return bookDao.insertBook(books);
    }


    public int removeBook(int id) {
        return bookDao.deleteBookById(id);
    }
}
