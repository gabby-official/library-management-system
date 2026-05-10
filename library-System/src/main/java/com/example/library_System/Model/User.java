package com.example.library_System.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
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

    @Email
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private ROLES role;

    @NotNull
    private String password;


    public User(int id, String lname, String fname, String department, int level, String email, ROLES role, String password) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.department = department;
        this.level = level;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User() {
    }

    public @NotNull @NotBlank String getFname() {
        return fname;
    }

    public void setFname(@NotNull @NotBlank String fname) {
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getLname() {
        return lname;
    }

    public void setLname(@NotNull @NotBlank String lname) {
        this.lname = lname;
    }

    public @NotNull @NotBlank String getDepartment() {
        return department;
    }

    public void setDepartment(@NotNull @NotBlank String department) {
        this.department = department;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public @NotNull int getLevel() {
        return level;
    }

    public void setLevel(@NotNull int level) {
        this.level = level;
    }

    public ROLES getRole() {
        return role;
    }

    public void setRole(ROLES role) {
        this.role = role;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}
