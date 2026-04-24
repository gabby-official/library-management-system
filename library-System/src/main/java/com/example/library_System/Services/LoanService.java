package com.example.library_System.Services;

import com.example.library_System.Model.Book;
import com.example.library_System.Model.Loan;
import com.example.library_System.Model.User;
import com.example.library_System.Repository.BookRepo;
import com.example.library_System.Repository.LoanRepo;
import com.example.library_System.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    // ➤ Borrow a book
    public Loan borrowBook(int userId, int bookId) {

        // Get user and book
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if book is already borrowed
        if (loanRepo.isBookBorrowed(bookId)) {
            throw new RuntimeException("Book is already borrowed");
        }

        // Create loan
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowDate(new Date());
        loan.setReturnDate(null);
        loan.setStatus("BORROWED");

        // Update book availability
        book.setAvailable(false);
        bookRepo.save(book);

        return loanRepo.save(loan);
    }

    // ➤ Return a book
    public Loan returnBook(int bookId) {

        Loan loan = loanRepo.findActiveLoanByBook(bookId);

        if (loan == null) {
            throw new RuntimeException("No active loan found for this book");
        }

        loan.setReturnDate(new Date());
        loan.setStatus("RETURNED");

        // Update book availability
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepo.save(book);

        return loanRepo.save(loan);
    }

    // ➤ Get all loans
    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    // ➤ Get active loans
    public List<Loan> getActiveLoans() {
        return loanRepo.findAllActiveLoans();
    }

    // ➤ Get loans by user
    public List<Loan> getLoansByUser(int userId) {
        return loanRepo.findByUserId(userId);
    }

    // ➤ Get active loans by user
    public List<Loan> getActiveLoansByUser(int userId) {
        return loanRepo.findActiveLoansByMember(userId);
    }
}