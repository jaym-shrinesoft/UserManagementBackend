package com.example.UserManagement.repository;

import com.example.UserManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    @Query("SELECT u FROM Users u WHERE u.emailAddress = ?1 AND u.password = ?2")
    List<Users> findByUserEmailandPassword(String emailAddress, String password);
    @Query("SELECT u from Users u WHERE u.userName = ?1")
    List<Users> findByUsername(String username);
}
