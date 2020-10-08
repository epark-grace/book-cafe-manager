package dev.park.e.controller;

import dev.park.e.dto.Book;
import dev.park.e.dto.Pagination;
import dev.park.e.service.BookService;
import dev.park.e.service.CategoryService;
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
    private CategoryService categoryService;

    public ViewController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
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

        List<Book> bookList = bookService.getBookList(currentPage, params);
        Pagination pagination = bookService.getPagination(currentPage, params);

        if (!params.isEmpty()) {
            StringBuilder search = new StringBuilder("?");
            for (String key : params.keySet()) {
                search.append(key).append("=").append(params.get(key));
            }
            model.addAttribute("search", search.toString());
        }

        model.addAttribute("bookList", bookList);
        model.addAttribute("pagination", pagination);
        model.addAttribute("categories", categoryService.getCategories());

        return "book-list";
    }
}