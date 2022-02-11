package com.example.UserManagement.service;

import com.example.UserManagement.model.Users;

import java.util.List;

public interface UsersService {
    public List<Users> getAllUsers();
    public Users saveUser(Users user);
    public void deleteUser(Long userId);
    public List<Users> findByEmailandPassword(Users user);
    public Users getUserById(Long id);
}
