package com.hotelreservationsystem.model;

import java.time.LocalDateTime;

/**
 * User - Model class representing a system user
 * Encapsulates user account information including authentication credentials and access control
 * Each user can be Admin, Receptionist, or Guest
 * Implements OOP principles: Encapsulation, Data Hiding
 */
public class User {

    private int userId;
    private String username;
    private String password; // Stored as hash, never plain text
    private String email;
    private String accessLevel; // Admin, Receptionist, or Guest
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor
     */
    public User() {
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor with basic user information
     * @param username Username for login
     * @param password Hashed password for login
     * @param email User email address
     * @param accessLevel User access level (Admin, Receptionist, Guest)
     */
    public User(String username, String password, String email, String accessLevel) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    /**
     * Constructor with all fields
     */
    public User(int userId, String username, String password, String email, String accessLevel, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ============ GETTERS AND SETTERS ============

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ============ BUSINESS LOGIC METHODS ============

    /**
     * Checks if user has admin access
     * @return true if access level is Admin
     */
    public boolean isAdmin() {
        return "Admin".equals(this.accessLevel);
    }

    /**
     * Checks if user is a receptionist
     * @return true if access level is Receptionist
     */
    public boolean isReceptionist() {
        return "Receptionist".equals(this.accessLevel);
    }

    /**
     * Checks if user is a guest
     * @return true if access level is Guest
     */
    public boolean isGuest() {
        return "Guest".equals(this.accessLevel);
    }

    /**
     * Checks if user account is active and can login
     * @return true if account is active
     */
    public boolean canLogin() {
        return this.isActive && this.username != null && this.password != null;
    }

    /**
     * Updates the updatedAt timestamp to current time
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns string representation of User object
     * @return Formatted user information
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Checks if two User objects are equal based on userId
     * @param obj Object to compare
     * @return true if objects represent same user
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId == user.userId;
    }

    /**
     * Returns hash code based on userId
     * @return Hash code of user
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}
