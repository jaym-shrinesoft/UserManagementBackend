package com.example.UserManagement.model;

public class JwtResponse {

    private String token;
    private Long userId;
    private String username;
    private String roleName;
    private String email;

    public JwtResponse() {
    }

    public JwtResponse(String token,Long userId, String username,String email, String roleName) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}