package com.example.ys;

public class Post {
    private String User;
    private int _id;
    private  String Password;
    private  String Role;

    public Post(String User, String Password, String Role) {
        this.User=User;
        this.Password=Password;
        this.Role=Role;
    }

    public String getUser() {
        return User;
    }

    public int get_id() {
        return _id;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }
}
