package com.example.dqb478_lab5.model;

import java.util.List;

/**
 * Represents a user in the system, typically a cast member in a theatrical production.
 * This class holds user credentials, real name, and a list of roles played by the user.
 */
public class User {
    private String username;
    private String password;
    private String realName;
    private List<Role> roles;

    /**
     * Constructs a new User with the specified credentials and roles.
     *
     * @param username The username of the user.
     * @param password The password for the user's account.
     * @param realName The real name of the user.
     * @param roles    A list of roles assigned to the user.
     */
    public User(String username, String password, String realName, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = roles;
    }

    /**
     * Validates the user credentials.
     *
     * @param inputUsername The username to validate.
     * @param inputPassword The password to validate.
     * @return true if the provided credentials match the user's credentials, false otherwise.
     */
    public boolean validate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Getters and Setters

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the real name of the user.
     *
     * @return The real name of the user.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Sets the real name of the user.
     *
     * @param realName The real name to be set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Gets the list of roles of the user.
     *
     * @return A list of roles assigned to the user.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles for the user.
     *
     * @param roles The list of roles to be set.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
