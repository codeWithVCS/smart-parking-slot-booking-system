package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing parking bookings in a login-free environment.
 * Handles creation, retrieval, cancellation, and completion of bookings.
 */
public interface BookingService {
    Booking createBooking(String userName, String userEmail, String userPhone,
                          String vehicleNumber, String slotId,
                          LocalDateTime startTime, LocalDateTime endTime);

    List<Booking> getAllBookings();
    List<Booking> getBookingsByUserPhone(String userPhone);
    Optional<Booking> getBookingById(String bookingId);
    void cancelBooking(String bookingId);
    void completeBooking(String bookingId);
}
