package com.example.firebase_1;

public class User {

    //name as firebase database

    private String Name;
    private String Username;
    private String Password;
    private String Email;

    public User() {
    }

    public User(String Name, String Username, String Password, String Email) {
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
