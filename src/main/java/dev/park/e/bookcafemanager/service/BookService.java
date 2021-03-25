package dev.park.e.bookcafemanager.service;

import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.domain.Category;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.repository.BookMapper;
import dev.park.e.bookcafemanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    final CategoryService categoryService;
    final BookMapper bookMapper;
    final BookRepository bookRepository;

    @Transactional
    public void createBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Transactional
    public long deleteBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("BookService.deleteBookById(\" + id + \"): 존재하지않는 Book ID"));
        bookRepository.deleteById(book.getId());
        return id;
    }

    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("BookService.getBookById(" + id + "): 존재하지않는 Book ID"));
    }

    @Transactional(readOnly = true)
    public List<BookDto.Response> getBooksBySearch(int currentPage, Search search) {
        return bookMapper.findAllBy(Pagination.getRowCount(currentPage), Pagination.ROW_LIMIT, search);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooks(int currentPage) {
        return bookRepository.findAll(Pagination.getRowCount(currentPage), Pagination.ROW_LIMIT);
    }

    @Transactional
    public Book updateBook(long id, BookDto.Request dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("BookService.updateBook(" + id + "): 존재하지않는 Book ID"));
        Category category = categoryService.getCategory(dto.getCategoryId());;
        book.update(category, dto.getTitle(), dto.getAuthor(), dto.getPublisher(), dto.getVolume(), dto.getShelfName(), dto.getRowNumber(), dto.getFinished(), dto.getForAdult());
        return book;
    }

    @Transactional
    public void updateShelfName(String existingShelfName, String newShelfName) {
        List<Book> books = bookRepository.findByShelfName(existingShelfName);
        for (Book book : books) {
            book.updateShelfName(newShelfName);
        }
    }
}
