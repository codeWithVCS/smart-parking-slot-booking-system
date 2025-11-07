package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing parking bookings.
 * Handles creation, cancellation, completion, and retrieval of bookings.
 */
public interface BookingService{
    Booking createBooking(String userId, String slotId, LocalDateTime startTime,  LocalDateTime endTime);
    List<Booking> getAllBookings();
    List<Booking> getBookingsByUser(String userId);
    void cancelBooking(String bookingId);
    void completeBooking(String bookingId);
    Optional<Booking> getBookingById(String bookingId);
}
