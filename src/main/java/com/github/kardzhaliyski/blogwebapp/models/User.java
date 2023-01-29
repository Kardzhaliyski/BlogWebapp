package com.github.kardzhaliyski.blogwebapp.models;

public class User {
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
