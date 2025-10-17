package org.kanakkupetti.model;

import java.time.LocalDateTime;

public class User {
    private int userId;
    private String userName;
    private String role;
    private LocalDateTime lastLoggedIn;

    public User(int userId, String userName, String role, LocalDateTime lastLoggedIn) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.lastLoggedIn = lastLoggedIn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(LocalDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", lastLoggedIn=" + lastLoggedIn +
                '}';
    }
}
