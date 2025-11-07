package org.chandra.smartparkingslotbookingsystem.model;

import java.time.LocalDateTime;

/**
 * Represents a reservation made by a user for a specific parking slot.
 * Stores booking period, status, and computed amount.
 */
public class Booking {
    private String bookingId;
    private String userId;
    private String slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private double totalAmount;

    public Booking(){}
    public Booking(String bookingId, String userId, String slotId, LocalDateTime startTime, LocalDateTime endTime,  BookingStatus status, double totalAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {return bookingId;}
    public void setBookingId(String bookingId) {this.bookingId = bookingId;}
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
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
        return String.format("Booking[%s] Slot=%s | User=%s | Start=%s | End=%s | Status=%s | Amount=%.2f",
                bookingId, slotId, userId, startTime, endTime, status, totalAmount);
    }
}
