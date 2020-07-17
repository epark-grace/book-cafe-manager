package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.dto.Pagination;
import dev.park.e.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class ViewController {

    private BookService bookService;

    public ViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/book-list")
    public String defaultBookList() {
        return "redirect:/book-list/1";
    }

    @GetMapping("/book-list/{currentPage}")
    public String bookList(@PathVariable(name = "currentPage") int currentPage,
                           @RequestParam HashMap<String, String> params,
                           Model model) {

        List<Book> bookList;
        Pagination pagination;

        if (params.isEmpty()) {
            bookList = bookService.getBookList(currentPage);
            pagination = bookService.getPagination(currentPage);
        } else {
            String column = "title";
            if (params.get("author") != null) {
                column = "author";
            }

            String keyword = params.get(column);

            bookList = bookService.searchBook(currentPage, column, keyword);
            pagination = bookService.getPagination(currentPage, column, keyword);

            StringBuilder search = new StringBuilder("?");
            search.append(column).append("=").append(keyword);

            model.addAttribute("search", search.toString());
        }

        model.addAttribute("bookList", bookList);
        model.addAttribute("pagination", pagination);

        return "book_list";
    }
}