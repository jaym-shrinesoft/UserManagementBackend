package com.example.UserManagement.service;

import com.example.UserManagement.model.Users;
import com.example.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users saveUser(Users user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<Users> findByEmailandPassword(Users user) {
        return userRepository.findByUserEmailandPassword(user.getEmailAddress(),user.getPassword());
    }

    @Override
    public Users getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }

    @Override
    public List<Users> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).get(0);
    }
}
