package com.example.library_System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String fname;

    @NotNull
    @NotBlank
    private String lname;

    @NotNull
    @NotBlank
    private String department;

    @NotNull
    private int level;

    public User(int id, String fname, String lname, int level, String department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.level = level;
        this.department = department;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull int getLevel() {
        return level;
    }

    public void setLevel(@NotNull int level) {
        this.level = level;
    }

    public @NotNull @NotBlank String getDepartment() {
        return department;
    }

    public void setDepartment(@NotNull @NotBlank String department) {
        this.department = department;
    }

    public @NotNull @NotBlank String getLname() {
        return lname;
    }

    public void setLname(@NotNull @NotBlank String lname) {
        this.lname = lname;
    }

    public @NotNull @NotBlank String getFname() {
        return fname;
    }

    public void setFname(@NotNull @NotBlank String fname) {
        this.fname = fname;
    }
}
