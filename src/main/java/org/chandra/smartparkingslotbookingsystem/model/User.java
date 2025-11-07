package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Represents a user of the parking system in a login-free environment.
 * Each user is identified by their contact details (name, email, phone number).
 */
public class User {
    private String name;
    private String email;
    private String phoneNumber;

    public User(){}
    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    @Override
    public String toString() {
        return String.format("User: %s | %s | %s",
                name, email, phoneNumber);
    }
}
