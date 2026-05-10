package com.example.library_System.Services;
import com.example.library_System.Model.ROLES;
import com.example.library_System.Model.User;
import com.example.library_System.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // ➤ Add new user
    public User addUser(User user) {

        if(userRepo.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if(user.getFname().isEmpty() ||
                user.getLname().isEmpty() ||
                user.getDepartment().isEmpty() ||
                user.getEmail().isEmpty() ||
                user.getPassword().isEmpty()){

            throw new RuntimeException("All fields are required");
        }
        if(user.getPassword().length() < 6){
            throw new RuntimeException("Password must be at least 6 characters");
        }
        user.setRole(ROLES.NORMAL_USER);
        return userRepo.save(user);
    }
    // ➤ Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // ➤ Get user by ID
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ➤ Delete user
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    // ➤ Update user
    public User updateUser(int id, User updatedUser) {
        User user = getUserById(id);

        user.setFname(updatedUser.getFname());
        user.setLname(updatedUser.getLname());
        user.setDepartment(updatedUser.getDepartment());
        user.setLevel(updatedUser.getLevel());

        return userRepo.save(user);
    }

    // ➤ Search by first or last name
    public List<User> searchByName(String fname, String lname) {
        return userRepo.findByFnameOrLname(fname, lname);
    }

    // ➤ Search by department
    public List<User> getByDepartment(String department) {
        return userRepo.findByDepartment(department);
    }

    // ➤ Search by level
    public List<User> getByLevel(int level) {
        return userRepo.findByLevel(level);
    }

    public User postLogin(String email, String password){

        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new RuntimeException("User doesn't exist");
        }

        if(user.getPassword().equals(password)){
            return user;
        }
        else{
            throw new RuntimeException("Password doesn't match");
        }
    }
}