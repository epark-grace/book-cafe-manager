package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("api/books")
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return bookService.addBooks(books);
    }

    @DeleteMapping("api/books/{id}")
    public Map<String, Object> removeBook(@PathVariable(name = "id") int id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", bookService.removeBook(id));
        resultMap.put("bookInfo", bookService.getBook(id));
        return resultMap;
    }

    @GetMapping("api/books/{id}")
    public Book getBook(@PathVariable(name = "id") int id) { return bookService.getBook(id); }

    @PutMapping("api/books/{id}")
    public Map<String, Object> updateBook(@RequestBody Book book) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", bookService.updateBook(book));
        resultMap.put("bookInfo", bookService.getBook(book.getId()));
        return resultMap;
    }
}