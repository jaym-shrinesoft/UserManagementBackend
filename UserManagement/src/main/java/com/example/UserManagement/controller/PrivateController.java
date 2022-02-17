package com.example.UserManagement.controller;

import com.example.UserManagement.mail.MailSender;
import com.example.UserManagement.model.Role;
import com.example.UserManagement.model.Users;
import com.example.UserManagement.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class PrivateController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/getAll")
    public List<Users> getAllUsers() {
        List<Users> newUserList = usersService.getAllUsers();
        for (Users u : newUserList) {
            Role role = new Role();
            role.setRoleName(u.getRole().getRoleName());
            u.setRole(role);
        }
        return newUserList;
    }
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        usersService.deleteUser(userId);
        return "User is deleted";
    }

    @GetMapping("/user/{userid}")
    private Users getUserbyId(@PathVariable("userid") Long userid)
    {
        Users user = usersService.getUserById(userid);
        Role role = new Role();
        role.setRoleName(user.getRole().getRoleName());
        user.setRole(role);
        return user;
    }
    @GetMapping("/username/{userName}")
    private Users getUserbyUsername(@PathVariable("userName") String userName)
    {
        List<Users> user = usersService.getUserByUsername(userName);
        Users newUser = user.get(0);
        Role role = new Role();
        role.setRoleName(newUser.getRole().getRoleName());
        newUser.setRole(role);
        return newUser;
    }
}