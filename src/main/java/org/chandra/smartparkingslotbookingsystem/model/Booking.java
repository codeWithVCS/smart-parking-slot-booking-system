package org.chandra.smartparkingslotbookingsystem.model;

import java.time.LocalDateTime;

/**
 * Represents a reservation made by a user for a specific parking slot.
 * Includes user contact and vehicle details in a login-free environment.
 */
public class Booking {
    private String bookingId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String vehicleNumber;
    private String slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private double totalAmount;

    public Booking(){}
    public Booking(String bookingId, String userName, String userEmail, String userPhone,String vehicleNumber, String slotId, LocalDateTime startTime, LocalDateTime endTime, BookingStatus status, double totalAmount) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.vehicleNumber = vehicleNumber;
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {return bookingId;}
    public void setBookingId(String bookingId) {this.bookingId = bookingId;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserPhone() {return userPhone;}
    public void setUserPhone(String userPhone) {this.userPhone = userPhone;}
    public String getSlotId() {return slotId;}
    public void setSlotId(String slotId) {this.slotId = slotId;}
    public LocalDateTime getStartTime() {return startTime;}
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}
    public LocalDateTime getEndTime() {return endTime;}
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}
    public BookingStatus getStatus() {return status;}
    public void setStatus(BookingStatus status) {this.status = status;}
    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    @Override
    public String toString() {
        return String.format(
                "Booking[%s] | User: %s (%s, %s) | Vehicle: %s | Slot: %s | " +
                        "From: %s | To: %s | Status: %s | Amount: ₹%.2f",
                bookingId, userName, userEmail, userPhone, vehicleNumber,
                slotId, startTime, endTime, status, totalAmount);
    }
}
