package com.example.UserManagement.controller;

import com.example.UserManagement.model.Role;
import com.example.UserManagement.model.Users;
import com.example.UserManagement.service.UsersService;
import com.example.UserManagement.utils.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailUtility emailUtility;

    @GetMapping("/getAll")
    public List<Users> getAllUsers(){
        List<Users> newUserList= usersService.getAllUsers();
        for (Users u : newUserList) {
            Role role = new Role();
            role.setRoleName(u.getRole().getRoleName());
            u.setRole(role);
        }
        return newUserList;
    }

    @PostMapping("/create")
    public String saveUsers(@RequestBody Users user){
        final String s = user.getPassword();
        final byte[] authBytes = s.getBytes(StandardCharsets.UTF_8);
        final String encoded = Base64.getEncoder().encodeToString(authBytes);
        user.setPassword(encoded);
        try{
            usersService.saveUser(user);
//            emailUtility.sendmail(user.getEmailAddress());
        }
        catch(Exception e){
            return "Duplicate";
        }
        return "New user is added";
    }

    @PostMapping("/login")
    public List<Users> login(@RequestBody Users user){
        final String s = user.getPassword();
        final byte[] authBytes = s.getBytes(StandardCharsets.UTF_8);
        final String encoded = Base64.getEncoder().encodeToString(authBytes);
        user.setPassword(encoded);
        List<Users> newUserList= usersService.findByEmailandPassword(user);
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
    private Users getBooks(@PathVariable("userid") Long userid)
    {
        Users user = usersService.getUserById(userid);
        Role role = new Role();
        role.setRoleName(user.getRole().getRoleName());
        user.setRole(role);
        return user;
    }
    @PatchMapping("/update/{id}")
    public String updateUserPartially(
            @PathVariable(value = "id") Long userId,
            @RequestBody Users userDetails) {
        final String s = userDetails.getPassword();
        final byte[] authBytes = s.getBytes(StandardCharsets.UTF_8);
        final String encoded = Base64.getEncoder().encodeToString(authBytes);
        userDetails.setPassword(encoded);
        Users user = usersService.getUserById(userId);
        user.setPassword(userDetails.getPassword());
        final Users updatedUser = usersService.saveUser(user);
        return "Password is updated";
    }
}
