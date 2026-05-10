package com.example.library_System.controllers;

import com.example.library_System.Model.Book;
import com.example.library_System.Model.ROLES;
import com.example.library_System.Model.User;
import com.example.library_System.Services.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // =========================
    // SHOW ALL BOOKS
    // =========================
    @GetMapping("/books")
    public String getAllBooks(Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if(user == null){
            return "redirect:/login";
        }

        model.addAttribute("books", bookService.getAllBooks());

        return "books";
    }

    // =========================
    // ADD BOOK PAGE (ADMIN ONLY)
    // =========================
    @GetMapping("/add_book")
    public String getBookPage(Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if(user == null || user.getRole() != ROLES.ADMIN_USER){
            return "redirect:/login";
        }

        model.addAttribute("book", new Book());

        return "add_book";
    }

    // =========================
    // SAVE BOOK (ADMIN ONLY)
    // =========================
    @PostMapping("/add_book")
    public String addBook(@ModelAttribute Book book,
                          Model model,
                          HttpSession session) {

        try {

            User user = (User) session.getAttribute("loggedInUser");

            if(user == null || user.getRole() != ROLES.ADMIN_USER){
                return "redirect:/login";
            }

            bookService.addBook(book);

            return "redirect:/admin";

        }
        catch (RuntimeException e) {

            model.addAttribute("error", e.getMessage());

            return "add_book";
        }
    }

    // =========================
    // DELETE BOOK (ADMIN ONLY)
    // =========================
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id,
                             HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if(user == null || user.getRole() != ROLES.ADMIN_USER){
            return "redirect:/login";
        }

        bookService.deleteBook(id);

        return "redirect:/books";
    }

    // =========================
    // EDIT PAGE (ADMIN ONLY)
    // =========================
    @GetMapping("/books/edit/{id}")
    public String editBookPage(@PathVariable int id,
                               Model model,
                               HttpSession session) {

        try {

            User user = (User) session.getAttribute("loggedInUser");

            if(user == null || user.getRole() != ROLES.ADMIN_USER){
                return "redirect:/login";
            }

            model.addAttribute("book", bookService.getBookById(id));

            return "edit_book";

        }
        catch (RuntimeException e){

            model.addAttribute("error", e.getMessage());

            return "books";
        }
    }

    // =========================
    // UPDATE BOOK
    // =========================
    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable int id,
                             @ModelAttribute Book book,
                             Model model,
                             HttpSession session) {

        try {

            User user = (User) session.getAttribute("loggedInUser");

            if(user == null || user.getRole() != ROLES.ADMIN_USER){
                return "redirect:/login";
            }

            bookService.updateBook(id, book);

            return "redirect:/books";

        }
        catch (RuntimeException e){

            model.addAttribute("error", e.getMessage());

            return "edit_book";
        }
    }

    // =========================
    // SEARCH
    // =========================
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String keyword,
                              Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if(user == null){
            return "redirect:/login";
        }

        model.addAttribute(
                "books",
                bookService.searchByTitleOrAuthor(keyword)
        );

        return "books";
    }
}