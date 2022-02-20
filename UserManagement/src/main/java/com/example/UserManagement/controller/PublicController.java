package com.example.UserManagement.controller;

import com.example.UserManagement.JWT.JwtTokenUtil;
import com.example.UserManagement.mail.MailSender;
import com.example.UserManagement.model.JwtRequest;
import com.example.UserManagement.model.JwtResponse;
import com.example.UserManagement.model.MailModel;
import com.example.UserManagement.model.Users;
import com.example.UserManagement.service.JwtUserDetailsService;
import com.example.UserManagement.service.UsersService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class PublicController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/create")
    public String saveUsers(@RequestBody Users user){
        final String s = user.getPassword();
        String encodedPassword=bCryptPasswordEncoder.encode(s);
        user.setPassword(encodedPassword);

        try{
            Users userEntity = usersService.saveUser(user);
            final String mailBody = "Hello " + user.getUserName() + ",<br/>" +
                    "Your activation link is: http://localhost:3000/createpassword/"+userEntity.getId();
            MailModel mailModel = new MailModel();
            mailModel.setTo(user.getEmailAddress());
            mailModel.setSubject("Account varification for "+ user.getUserName());
            mailModel.setBody(mailBody);
            mailSender.sendMail(mailModel);
            return String.valueOf(userEntity.getId());
        }
        catch(Exception e){
            return "Duplicate";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Users users)
            throws Exception {
        try{
            Users user = usersService.getUserByEmail(users.getEmailAddress());
            JwtRequest jwtRequest = new JwtRequest(user.getUserName(),users.getPassword());
            authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(jwtRequest.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new
                    JwtResponse(token,user.getId(),user.getUserName(), user.getEmailAddress(),user.getRole().getRoleName()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("User not found");
        }

    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("USER_DISABLED", e);
        }
    }
    @PatchMapping("/updatepasssword/{id}")
    public String updateUserPassword(
            @PathVariable(value = "id") Long userId,
            @RequestBody Users userDetails) {
        String encoded = bCryptPasswordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(encoded);
        Users user = usersService.getUserById(userId);
        user.setPassword(userDetails.getPassword());
        user.setStatus("active");
        final Users updatedUser = usersService.saveUser(user);
        return "Password is updated";
    }
}
