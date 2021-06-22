package com.moneelab.assignment.dto.user;

import com.moneelab.assignment.domain.user.User;

public class UserRequest {
    private String name;
    private String password;

    /**
     * Getter & Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        return User.createUser(name, password);
    }
}