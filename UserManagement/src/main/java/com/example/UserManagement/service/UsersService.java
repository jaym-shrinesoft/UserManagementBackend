package com.example.UserManagement.service;

import com.example.UserManagement.model.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {
    public List<Users> getAllUsers();
    public Users saveUser(Users user);
    public void deleteUser(Long userId);
    public List<Users> findByEmailandPassword(Users user);
    public Users getUserById(Long id);
    public List<Users> getUserByUsername(String username);
    public Users getUserByEmail(String email);
}
