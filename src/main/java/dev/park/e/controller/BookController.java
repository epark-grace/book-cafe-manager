package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("api/books")
    public int addBooks(@RequestBody List<Book> books) {
        return bookService.addBooks(books);
    }

    @DeleteMapping("api/books/{id}")
    public int removeBook(@PathVariable(name = "id") int id) { return bookService.removeBook(id); }
}