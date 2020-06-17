package dev.park.e.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
    @GetMapping("/")
    public String main() {
        return "redirect:/1";
    }

    @GetMapping("/{page}")
    public String bookList(@PathVariable(name = "page") String page) {
        return "bookList";
    }
}