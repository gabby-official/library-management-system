package com.example.library_System.Repository;

import com.example.library_System.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loan, Integer> {
    //All loans by a user
    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId")
    List<Loan> findByUserId(@Param("userId") Integer userId);

    //books currently with the particular user
    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId AND l.returnDate IS NULL")
    List<Loan> findActiveLoansByMember(@Param("userId") Integer userId);

    //Check if this book is currently borrowed by anyone and not yet returned.
    @Query("SELECT COUNT(l) > 0 FROM Loan l WHERE l.book.id = :bookId AND l.returnDate IS NULL")
    boolean isBookBorrowed(@Param("bookId") Integer bookId);

    //Active loan for a book
    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId AND l.returnDate IS NULL")
    Loan findActiveLoanByBook(@Param("bookId") Integer bookId);

    //Find all active loans
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL")
    List<Loan> findAllActiveLoans();

    boolean existsByBookId(int bookId);
}


