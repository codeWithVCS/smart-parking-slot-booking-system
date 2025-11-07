package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.Booking;
import org.chandra.smartparkingslotbookingsystem.model.BookingStatus;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.chandra.smartparkingslotbookingsystem.repository.BookingRepository;
import org.chandra.smartparkingslotbookingsystem.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link AdminService} providing system-level management
 * capabilities for parking slots and user bookings.
 */
@Service
public class AdminServiceImpl implements AdminService{
    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public AdminServiceImpl(SlotRepository slotRepository, BookingRepository bookingRepository) {
        this.slotRepository = slotRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Method to retrieve all the parking slots
     * @return a new {@link List} of all the {@link ParkingSlot}
     */
    @Override
    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    /**
     * Method to create a new {@link ParkingSlot} as an Admin
     * @param slot the new {@link ParkingSlot} that is to be created
     */
    @Override
    public void createSlot(ParkingSlot slot) {
        slotRepository.save(slot);
        System.out.printf("Admin added new slot: %s%n", slot);
    }

    /**
     * Method to update an existing {@link ParkingSlot} as an Admin
     * @param slot a new {@link ParkingSlot} that is to be updated
     */
    @Override
    public void updateSlot(ParkingSlot slot) {
        slotRepository.update(slot);
        System.out.printf("Admin updated slot: %s%n", slot);
    }

    /**
     * Method to delete an existing {@link ParkingSlot} as an Admin
     * @param slot the {@link ParkingSlot} that is to be deleted
     */
    @Override
    public void deleteSlot(ParkingSlot slot) {
        slotRepository.delete(slot);
        System.out.printf("Admin deleted slot: %s%n", slot);
    }

    /**
     * An Admin Method to view all the {@link Booking} in the repository
     * @return a {@link List} of {@link Booking}
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Method to cancel an existing {@link Booking} if exists, lse cancellation skipped
     * @param bookingId the unique identifier of the {@link Booking} to be cancelled
     */
    @Override
    public void cancelBookingAsAdmin(String bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(booking -> {
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                System.out.printf("Booking %s already cancelled.%n", bookingId);
                return;
            }
            bookingRepository.updateStatus(bookingId, BookingStatus.CANCELLED);
            System.out.printf("Admin forcibly cancelled booking %s.%n", bookingId);
        }, () -> System.out.printf("Booking %s not found — cannot cancel.%n", bookingId));
    }
}
