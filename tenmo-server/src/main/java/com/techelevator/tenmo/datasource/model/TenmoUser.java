package com.techelevator.tenmo.datasource.model;

import ch.qos.logback.classic.pattern.DateConverter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

public class TenmoUser {
    @Positive(message = "User ID cannot be negative.")

    private int user_id;
    @NotBlank(message = "Username cannot be blank.")

    private String username;
    @NotBlank(message = "Password cannot be blank.")

    private String password_hash;
    @NotBlank(message = "Role cannot be blank.")

    private String role;
    private Timestamp created_at;

    public TenmoUser(int user_id, String username, String password_hash, String role, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.password_hash = password_hash;
        this.role = role;
        this.created_at = created_at;
    }

    public TenmoUser() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "TenmoUser{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", role='" + role + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
