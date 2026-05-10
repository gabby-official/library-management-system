package com.example.library_System.Repository;

import com.example.library_System.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByFnameOrLname(String fname, String lname);

    List<User> findByDepartment(String department);

    List<User> findByLevel(int level);

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
