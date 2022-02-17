package com.example.UserManagement.service;

import com.example.UserManagement.model.Users;
import com.example.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Users user = userRepository.findByUsername(username).get(0);
            if (user.getUserName().equals(username)) {
                return new User(user.getUserName(), user.getPassword(),
                        new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }
        catch (Exception e){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}
