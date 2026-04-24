package com.example.library_System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private  String title;


    @NotNull
    @NotBlank
    private  String author;

    @NotNull
    private Boolean available;

    @NotNull
    @NotBlank
    private String category;

    public Book(int id, String title, String author, Boolean available, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.category = category;
    }


    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @NotBlank String title) {
        this.title = title;
    }

    public @NotNull @NotBlank String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull @NotBlank String author) {
        this.author = author;
    }

    public @NotNull @NotBlank Boolean getAvailable() {
        return available;
    }

    public void setAvailable(@NotNull @NotBlank Boolean available) {
        this.available = available;
    }

    public @NotNull @NotBlank String getCategory() {
        return category;
    }

    public void setCategory(@NotNull @NotBlank String category) {
        this.category = category;
    }
}
