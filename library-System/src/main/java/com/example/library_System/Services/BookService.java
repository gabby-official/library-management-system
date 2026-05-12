package com.example.library_System.Services;

import com.example.library_System.Model.Book;
import com.example.library_System.Repository.BookRepo;
import com.example.library_System.Repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private LoanRepo loanRepo;

    // ➤ Add new book
    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepo.save(book);
    }

    // ➤ Get all books
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // ➤ Get book by ID
    public Book getBookById(int id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ➤ Update book
    public Book updateBook(int id, Book updatedBook) {
        Book book = getBookById(id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());

        return bookRepo.save(book);
    }

    // ➤ Delete book
    public void deleteBook(int id) {

        if(loanRepo.existsByBookId(id)){
            throw new RuntimeException("Book has loan history and cannot be deleted");
        }

        bookRepo.deleteById(id);
    }
    // ➤ Search by title or author
    public List<Book> searchByTitleOrAuthor(String keyword){

        return bookRepo
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }


    // ➤ Get available books
    public List<Book> getAvailableBooks() {
        return bookRepo.findAvailableBooks();
    }

    // ➤ Get borrowed books
    public List<Book> getBorrowedBooks() {
        return bookRepo.findBorrowedBooks();
    }
}