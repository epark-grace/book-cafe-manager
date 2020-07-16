package dev.park.e.controller;

import dev.park.e.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "author", required = false) String author,
                           Model model) {

        if (title != null) {
            model.addAttribute("bookList", bookService.searchBook("title", title, currentPage));
            model.addAttribute("pagination", bookService.getPagination(currentPage, "title", title));
            model.addAttribute("search", "?title=" + title);
        } else if (author != null) {
            model.addAttribute("bookList", bookService.searchBook("author", author, currentPage));
            model.addAttribute("pagination", bookService.getPagination(currentPage, "author", author));
            model.addAttribute("search", "?author=" + author);
        } else {
            model.addAttribute("bookList", bookService.getBookList(currentPage));
            model.addAttribute("pagination", bookService.getPagination(currentPage));
        }
        return "book_list";
    }
}