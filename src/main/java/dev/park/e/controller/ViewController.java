package dev.park.e.controller;

import dev.park.e.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String bookList(@PathVariable(name = "currentPage") int currentPage, Model model) {
        model.addAttribute("pagination", bookService.getPagination(currentPage));
        model.addAttribute("bookList", bookService.getBookList(currentPage));
        return "book_list";
    }
}