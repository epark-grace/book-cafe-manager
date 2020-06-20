package dev.park.e.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/book-list/{currentPage}")
    public String bookList(@PathVariable(name = "currentPage") String page) {
        return "book_list";
    }
}