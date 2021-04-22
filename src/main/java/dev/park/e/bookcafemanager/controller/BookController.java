package dev.park.e.bookcafemanager.controller;

import dev.park.e.bookcafemanager.converter.Converter;
import dev.park.e.bookcafemanager.domain.Book;
import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.HttpResponseBody;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BookController {

    final BookService bookService;
    final Converter<Book> bookConverter;

    @PostMapping(value = "api/books", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> addBooks(@RequestBody List<BookDto.Request> requestDto) {
        List<Book> requestEntities = bookConverter.toEntities(requestDto);
        bookService.createBooks(requestEntities);
        HttpResponseBody body = new HttpResponseBody("등록되었습니다.", requestDto.size());
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "api/books/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> deleteBookById(@PathVariable(name = "id") long id) {
        HttpResponseBody body = new HttpResponseBody("삭제되었습니다.", bookService.deleteBookById(id));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping(value = "api/books", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> getBooksByAutoCompleteSearch(@ModelAttribute Search search) {
        List<BookDto.Response> booksDto = bookService.getBooksByAutoCompleteSearch(search);
        HttpResponseBody body = new HttpResponseBody("", booksDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping(value = "api/books/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> getBookById(@PathVariable(name = "id") long id) {
        BookDto.Response responseDto = new BookDto.Response(bookService.getBookById(id));
        HttpResponseBody body = new HttpResponseBody("조회되었습니다.", responseDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping(value = "api/books/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody> updateBookById(@RequestBody BookDto.Request requestDto, @PathVariable(name = "id") long id) {
        BookDto.Response responseDto = new BookDto.Response(bookService.updateBook(id, requestDto));
        HttpResponseBody body = new HttpResponseBody("수정되었습니다.", responseDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PatchMapping(value = "/books")
    public ResponseEntity<Void> updateShelfName(@RequestParam("shelf-name") String existingShelfName, @RequestBody Map<String, String> body) {
        bookService.updateShelfName(existingShelfName, body.get("newShelfName"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}