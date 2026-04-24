package com.example.library_System.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Date borrowDate;

    private Date returnDate;

    @NotNull
    private String status;


    public Loan(int id, Book book, User user, Date borrowDate, Date returnDate, String status) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Loan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull @NotBlank Book getBook() {
        return book;
    }

    public void setBook(@NotNull @NotBlank Book book) {
        this.book = book;
    }

    public @NotNull @NotBlank User getUser() {
        return user;
    }

    public void setUser(@NotNull @NotBlank User user) {
        this.user = user;
    }

    public @NotNull @NotBlank Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(@NotNull @NotBlank Date returnDate) {
        this.returnDate = returnDate;
    }

    public @NotNull @NotBlank Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(@NotNull @NotBlank Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public @NotNull @NotBlank String getStatus() {
        return status;
    }

    public void setStatus(@NotNull @NotBlank String status) {
        this.status = status;
    }
}
