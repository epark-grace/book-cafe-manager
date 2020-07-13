package dev.park.e.service;

import dev.park.e.dto.Book;
import dev.park.e.dto.Category;
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

    @Transactional(readOnly = true)
    public Pagination getPagination(int currentPage) {
        return new Pagination(bookMapper.selectBookCount(), currentPage);
    }

    @Transactional
    public List<Book> addBooks(List<Book> books) {
        bookMapper.insertBook(books);
        return books;
    }

    @Transactional
    public int removeBook(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Transactional(readOnly = true)
    public Book getBook(int id) {
        return bookMapper.selectBookById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> getBookList(int currentPage) {
        int rowCount = (currentPage - 1) * Pagination.ROW_LIMIT;
        return bookMapper.selectBookList(rowCount, Pagination.ROW_LIMIT);
    }

    public List<Book> setCategoryName(List<Book> books, List<Category> categories) {
        for (Book book : books) {
            if (book.getCategoryId() == null) {
                book.setCategoryName("미분류");
                continue;
            }
            for (Category category : categories) {
                int id = category.getId();
                if (book.getCategoryId() == id) {
                    book.setCategoryName(category.getName());
                    break;
                }
            }
        }

        return books;
    }
}
