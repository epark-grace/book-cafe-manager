package dev.park.e.service;

import dev.park.e.dto.Book;
import dev.park.e.dto.Category;
import dev.park.e.dto.Pagination;
import dev.park.e.mapper.BookMapper;
import dev.park.e.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class BookService {

    BookMapper bookMapper;
    CategoryMapper categoryMapper;

    public BookService(BookMapper bookMapper, CategoryMapper categoryMapper) {
        this.bookMapper = bookMapper;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true)
    public Pagination getPagination(int currentPage) {
        return new Pagination(bookMapper.selectBookCount(null), currentPage);
    }

    @Transactional(readOnly = true)
    public Pagination getPagination(int currentPage, String column, String keyword) {
        String[] words = getWordArray(keyword);
        HashMap<String, Object> search = new HashMap<>();
        search.put("column", column);
        search.put("words", words);
        return new Pagination(bookMapper.selectBookCount(search), currentPage);
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

    @Transactional
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Transactional
    public List<Book> searchBook(String column, String keyword, int currentPage) {
        String[] words = getWordArray(keyword);
        int rowCount = (currentPage - 1) * Pagination.ROW_LIMIT;
        return bookMapper.selectBookListByKeyword(column, words, rowCount, Pagination.ROW_LIMIT);
    }

    private String[] getWordArray(String keyword) {
        return keyword.split(" ");
    }
}
