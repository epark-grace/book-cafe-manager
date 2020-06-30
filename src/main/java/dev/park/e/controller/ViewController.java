package dev.park.e.controller;

import dev.park.e.dto.Pagination;
import dev.park.e.service.PaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    private PaginationService paginationService;

    public ViewController(PaginationService paginationService) {
        this.paginationService = paginationService;
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
        model.addAttribute("pagination", paginationService.getPagination(currentPage));
        return "book_list";
    }
}