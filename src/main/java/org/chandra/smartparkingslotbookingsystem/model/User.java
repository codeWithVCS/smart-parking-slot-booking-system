package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Represents a system user who can view, book, and manage parking slots.
 * Each user has a type (Regular or VIP) that can influence pricing or privileges.
 */
public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private UserType userType;

    public User(){}
    public User(String userId, String name, String email, String phoneNumber, UserType userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public UserType getUserType() {return userType;}
    public void setUserType(UserType userType) {this.userType = userType;}

    @Override
    public String toString() {
        return String.format("User[%s] %s | %s | %s | Type=%s",
                userId, name, email, phoneNumber, userType);
    }
}
