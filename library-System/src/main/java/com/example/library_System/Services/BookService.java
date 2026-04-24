package com.example.library_System.Services;

import com.example.library_System.Model.Book;
import com.example.library_System.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    // ➤ Add new book
    public Book addBook(Book book) {
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
        book.setAvailable(updatedBook.getAvailable());

        return bookRepo.save(book);
    }

    // ➤ Delete book
    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    // ➤ Search by title or author
    public List<Book> searchByTitleOrAuthor(String title, String author) {
        return bookRepo.findByTitleOrAuthor(title, author);
    }

    // ➤ Search by title
    public List<Book> searchByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    // ➤ Search by author
    public List<Book> searchByAuthor(String author) {
        return bookRepo.findByAuthor(author);
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