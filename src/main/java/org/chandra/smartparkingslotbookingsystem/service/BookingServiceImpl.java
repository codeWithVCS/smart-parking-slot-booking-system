package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.Booking;
import org.chandra.smartparkingslotbookingsystem.model.BookingStatus;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.chandra.smartparkingslotbookingsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link BookingService} responsible for managing bookings and their lifecycle.
 */
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final SlotService slotService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, SlotService slotService) {
        this.bookingRepository = bookingRepository;
        this.slotService = slotService;
    }

    /**
     * Method to create a new Booking
     * @param userId unique identifier of the user who is booking the slot
     * @param slotId unique identifier of the slot which is being booked
     * @param startTime booking start time
     * @param endTime booking end time
     * @return a new {@link Booking}
     */
    @Override
    public Booking createBooking(String userId, String slotId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<ParkingSlot> optionalSlot = slotService.getSlotById(slotId);
        if(optionalSlot.isEmpty()){
            System.out.printf("Slot %s not found — booking cannot be created.%n ", slotId);
            return null;
        }
        ParkingSlot slot =  optionalSlot.get();
        if(!slot.isAvailable()){
            System.out.printf("Slot %s is already booked — choose another slot.%n", slotId);
            return null;
        }
        //Calculate total cost
        long hours = Math.max(1, (long) Math.ceil(Duration.between(startTime, endTime).toMinutes() / 60.0));;
        double totalAmount = hours * slot.getHourlyRate();
        //Create booking ID
        String bookingId = "BKG-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        //Create and save booking
        Booking booking = new Booking(bookingId, userId, slotId, startTime, endTime, BookingStatus.ACTIVE, totalAmount);
        bookingRepository.save(booking);
        //Mark slot as booked
        slotService.markSlotAsBooked(slotId);
        System.out.printf("Booking %s created successfully for Slot %s (User %s).%n", bookingId, slotId, userId);
        return booking;
    }

    /**
     * Method to retrieve all the bookings available in the repository
     * @return a new {@link List} of {@link Booking}
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Method to retrieve all the bookings of a user based on the user id
     * @param userId unique identifier of the user who's booking are being retrieved
     * @return a new {@link List} of {@link Booking}
     */
    @Override
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    /**
     * Method to cancel a booking if it exists, else skipped if the status is already cancelled or booking doesn't exist
     * @param bookingId unique identifier of the booking that is to be cancelled
     */
    @Override
    public void cancelBooking(String bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(booking -> {
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                System.out.printf("Booking %s is already cancelled.%n", bookingId);
                return;
            }
            bookingRepository.updateStatus(bookingId, BookingStatus.CANCELLED);
            slotService.markSlotAsAvailable(booking.getSlotId());
            System.out.printf("Booking %s cancelled successfully.%n", bookingId);
        }, () -> System.out.printf("Booking %s not found — cancel operation skipped.%n", bookingId));
    }

    /**
     * Method to mark a booking as completed after the end time if the status is active, gets skipped if the status is already completed or booking doesn't exist
     * @param bookingId unique identifier of the booking which has to be marked as completed
     */
    @Override
    public void completeBooking(String bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(booking -> {
            if (booking.getStatus() == BookingStatus.COMPLETED) {
                System.out.printf("Booking %s is already completed.%n", bookingId);
                return;
            }
            bookingRepository.updateStatus(bookingId, BookingStatus.COMPLETED);
            slotService.markSlotAsAvailable(booking.getSlotId());
            System.out.printf("Booking %s marked as completed.%n", bookingId);
        }, () -> System.out.printf("Booking %s not found — complete operation skipped.%n", bookingId));
    }

    /**
     * Method to retrieve a booking by its id, if exists
     * @param bookingId unique identifier of the booking which is being searched for
     * @return a {@link Optional} {@link Booking}
     */
    @Override
    public Optional<Booking> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }
}
