package com.example.library_System.Repository;

import com.example.library_System.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author
    );

    // Get all available books
    @Query("SELECT b FROM Book b WHERE b.id NOT IN (SELECT l.book.id FROM Loan l WHERE l.returnDate IS NULL)")
    List<Book> findAvailableBooks();

//Borrowed books
    @Query("SELECT DISTINCT b FROM Book b JOIN Loan l ON b.id = l.book.id WHERE l.returnDate IS NULL")
    List<Book> findBorrowedBooks();

}
