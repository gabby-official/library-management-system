package com.example.library_System.controllers;

import com.example.library_System.Model.User;
import com.example.library_System.Services.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;


    @PostMapping("/borrow/{bookId}")
    public String borrow(@PathVariable int bookId,
                         HttpSession session,
                         Model model) {

        try {
            User user = (User) session.getAttribute("loggedInUser");

            if (user == null) {
                return "redirect:/login";
            }

            loanService.borrowBook(user.getId(), bookId);

            return "redirect:/myloans";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }

    // RETURN BOOK
    // =========================
    @GetMapping("/return")
    public String returnPage(){
        return "return";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable int id,
                             Model model){

        try {

            loanService.returnBook(id);

            return "redirect:/myloans";

        }
        catch (RuntimeException e){

            model.addAttribute("error", e.getMessage());

            return "loans";
        }
    }
    // VIEW LOANS
    // =========================
    @GetMapping("/myloans")
    public String getAllLoans(Model model, HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("loans", loanService.getLoansByUser(user.getId()));
        return "loans";
    }
}