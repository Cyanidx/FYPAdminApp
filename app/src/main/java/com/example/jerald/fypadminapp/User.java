package com.example.jerald.fypadminapp;

/**
 * Created by 15017292 on 24/5/2017.
 */

public class User {

    public String role;
    public String name;

    public User(String role, String name) {
        this.role = role;
        this.name = name;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
