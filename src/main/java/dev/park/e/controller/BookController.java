package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public int removeBook(@PathVariable(name = "id") int id) { return bookService.removeBook(id); }

    @GetMapping("api/books/{id}")
    public Book getBook(@PathVariable(name = "id") int id) {
        return bookService.getBook(id);
    }

    @PutMapping("api/books/{id}")
    public HashMap<String, Object> updateBook(@RequestBody Book book) {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", bookService.updateBook(book));
        resultMap.put("book", bookService.getBook(book.getId()));
        return resultMap;
    }
}