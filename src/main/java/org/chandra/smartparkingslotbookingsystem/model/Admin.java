package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Represents an administrative user of the parking management system.
 * Admins can create, update, or remove parking slots and manage bookings.
 */
public class Admin {
    private String adminId;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;

    public Admin(){}
    public Admin(String adminId, String name, String email, String phoneNumber, String role) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getAdminId() {return adminId;}
    public void setAdminId(String adminId) {this.adminId = adminId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    @Override
    public String toString() {
        return String.format("Admin[%s] %s | %s | %s | Role=%s",
                adminId, name, email, phoneNumber, role);
    }
}
