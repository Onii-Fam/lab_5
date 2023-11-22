package com.example.dqb478_lab5.model;

import java.util.List;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<Role> roles;

    public User(String username, String password, String realName, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = roles;
    }

    // Method to validate user credentials
    public boolean validate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }


    // Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}


