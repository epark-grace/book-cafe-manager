package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.dto.Book;
import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public Pagination getPagination(int currentPage, HashMap<String, String> search) {
        Map<String, String[]> searchParams = getSearchParameterOrNull(search);
        return new Pagination(bookMapper.selectBookCount(searchParams), currentPage);
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
    public List<Book> getBookList(int currentPage, HashMap<String, String> search) {
        Map<String, String[]> searchParams = getSearchParameterOrNull(search);
        int rowCount = (currentPage - 1) * Pagination.ROW_LIMIT;
        return bookMapper.selectBookList(searchParams, rowCount, Pagination.ROW_LIMIT);
    }

    @Transactional
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    private String[] getWordArray(String keyword) {
        return keyword.split(" ");
    }

    private Map<String, String[]> getSearchParameterOrNull(HashMap<String, String> search) {
        Map<String, String[]> searchParams = null;
        if (!search.isEmpty()) {
            String column = search.keySet().iterator().next();
            String[] words = getWordArray(search.get(column));
            searchParams = Collections.singletonMap(column, words);
        }

        return searchParams;
    }
}
