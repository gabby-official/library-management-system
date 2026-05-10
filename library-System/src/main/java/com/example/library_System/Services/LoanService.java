package com.example.library_System.Services;

import com.example.library_System.Model.Book;
import com.example.library_System.Model.Loan;
import com.example.library_System.Model.LoanStatus;
import com.example.library_System.Model.User;
import com.example.library_System.Repository.BookRepo;
import com.example.library_System.Repository.LoanRepo;
import com.example.library_System.Repository.UserRepo;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Loan borrowBook(int userId, int bookId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowDate(new Date());
        loan.setStatus(LoanStatus.BORROWED);

        book.setAvailable(false);

        bookRepo.save(book);
        return loanRepo.save(loan);
    }

    @Transactional
    public Loan returnBook(int bookId) {

        Loan loan = loanRepo.findActiveLoanByBook(bookId);

        if (loan == null) {
            throw new RuntimeException("No active loan found");
        }

        loan.setReturnDate(new Date());
        loan.setStatus(LoanStatus.RETURNED);

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