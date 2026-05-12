package com.example.library_System.controllers;

import com.example.library_System.Model.ROLES;
import com.example.library_System.Model.User;
import com.example.library_System.Services.BookService;
import com.example.library_System.Services.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;

    @Autowired
    LoanService loanService;

    @GetMapping("/admin")
    public String adminPage(HttpSession session,
                            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                user.getRole() != ROLES.ADMIN_USER) {

            return "redirect:/login";
        }

        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );

        return "admin_dashboard";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id,
                             Model model) {

        try {

            bookService.deleteBook(id);

            model.addAttribute("message", "Book deleted");
            model.addAttribute("books", bookService.getAllBooks());

            return "admin_dashboard";
        }
        catch (RuntimeException e){

            model.addAttribute("message", e.getMessage());
            model.addAttribute("books", bookService.getAllBooks());

            return "admin_dashboard";
        }
    }


    @GetMapping("/admin/loans")
    public String allLoans(Model model,
                           HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

       if (user == null || user.getRole() != ROLES.ADMIN_USER) {
            return "redirect:/login";
        }

        model.addAttribute("loans", loanService.getAllLoans());

        return "loans";
    }
}
