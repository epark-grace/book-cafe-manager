package dev.park.e.bookcafemanager.controller;

import dev.park.e.bookcafemanager.dto.BookDto;
import dev.park.e.bookcafemanager.dto.CategoryDto;
import dev.park.e.bookcafemanager.dto.Pagination;
import dev.park.e.bookcafemanager.dto.Search;
import dev.park.e.bookcafemanager.service.BookService;
import dev.park.e.bookcafemanager.service.CategoryService;
import dev.park.e.bookcafemanager.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final PaginationService paginationService;

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
                           @ModelAttribute("search") Search search,
                           Model model) {

        List<BookDto.Response> bookList;

        if (search.getCriteria() != null) {
            bookList = bookService.getBooksBySearch(currentPage, search);
        } else {
            bookList = BookDto.Response.listOf(bookService.getBooks(currentPage));
        }

        Pagination pagination = paginationService.getPagination(currentPage, search);
        List<CategoryDto.Response> categories = CategoryDto.Response.listOf(categoryService.getCategories());

        model.addAttribute("pagination", pagination);
        model.addAttribute("bookList", bookList);
        model.addAttribute("categories", categories);

        return "book-list";
    }

    @GetMapping("/shelf-editor")
    public String shelfEditor() {
        return "shelf-editor";
    }

    @GetMapping("/book-creation-form")
    public String bookCreationForm(Model model) {
        List<CategoryDto.Response> categoriesDto = CategoryDto.Response.listOf(categoryService.getCategories());
        model.addAttribute("categories", categoriesDto);
        return "book-creation-form";
    }
}