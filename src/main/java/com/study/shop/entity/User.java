package com.study.shop.entity;

import com.study.shop.security.entity.UserRole;

public class User {
    String name;
    String password;
    UserRole userRole;

    public User(String name, String password, UserRole userRole) {
        this.name = name;
        this.password = password;
        this.userRole = userRole;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
