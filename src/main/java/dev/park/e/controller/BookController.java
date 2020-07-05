package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("api/books")
    public int postBooks(@RequestBody List<Book> books) {
        return bookService.addBooks(books);
    }
}