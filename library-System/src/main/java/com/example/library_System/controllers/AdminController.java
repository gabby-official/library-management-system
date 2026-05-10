package com.example.library_System.controllers;

import com.example.library_System.Model.ROLES;
import com.example.library_System.Model.User;
import com.example.library_System.Services.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;

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
}
