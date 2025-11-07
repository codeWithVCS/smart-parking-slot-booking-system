package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.Booking;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;

import java.util.List;

/**
 * Service interface defining administrative operations for managing
 * parking slots and user bookings.
 */
public interface AdminService {
    List<ParkingSlot> getAllSlots();
    void createSlot(ParkingSlot parkingSlot);
    void updateSlot(ParkingSlot parkingSlot);
    void deleteSlot(ParkingSlot parkingSlot);
    List<Booking> getAllBookings();
    void cancelBookingAsAdmin(String bookingId);
}
