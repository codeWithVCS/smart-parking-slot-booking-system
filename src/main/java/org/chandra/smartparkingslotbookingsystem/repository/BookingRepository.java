package org.chandra.smartparkingslotbookingsystem.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.chandra.smartparkingslotbookingsystem.model.Booking;
import org.chandra.smartparkingslotbookingsystem.model.BookingStatus;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In-memory repository for managing {@link Booking} entities.
 * <p>
 * Stores all bookings in a transient list shared across profiles.
 * Supports CRUD operations and booking status management.
 * </p>
 */
@Repository
public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    /**
     * Logs the initialization of in-memory Booking repository
     * after the Spring Context is fully loaded
     */
    @PostConstruct
    public void init() {
        System.out.println("📘 BookingRepository initialized — ready to manage bookings.");
    }

    /**
     * Clears in-memory data before the Spring context is destroyed
     */
    @PreDestroy
    public void shutdown() {
        System.out.println("BookingRepository shutting down — clearing in-memory data...");
        bookings.clear();
    }

    /**
     * Retrieves the count of bookings available in the repository
     * @return the number of bookings
     */
    public long count() {
        return bookings.size();
    }

    /**
     * Retrieves all the Bookings currently available in the repository
     *
     * @return a new {@link List} containing all stored {@link Booking} objects
     */
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    /**
     * Finds a Booking by its unique ID.
     *
     * @param bookingId the identifier of the Booking to search for
     * @return an {@link Optional} containing the {@link Booking} if found, otherwise empty
     */
    public Optional<Booking> findById(String bookingId) {
        return bookings.stream()
                .filter(b -> b.getBookingId().equalsIgnoreCase(bookingId))
                .findFirst();
    }

    /**
     * Retrieves all the bookings of an user by the user ID
     * @param userId the identifier of the user who's bookings are being searched for
     * @return a new {@link List} containing all the {@link Booking} of the specified user
     */
    public List<Booking> findByUserId(String userId) {
        return bookings.stream()
                .filter(b -> b.getUserId().equalsIgnoreCase(userId))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new booking to the repository
     * @param booking the {@link Booking} instance to be added
     */
    public void save(Booking booking) {
        bookings.add(booking);
        System.out.printf("Booking %s added successfully.%n", booking.getBookingId());
    }

    /**
     * Updates the status (active/completed/cancelled) of booking, if it exists
     * @param bookingId the identifier of the booking who's status is to be updated
     * @param newStatus the new status that is to be set (active/completed/cancelled)
     */
    public void updateStatus(String bookingId, BookingStatus newStatus) {
        findById(bookingId).ifPresentOrElse(existing -> {
            existing.setStatus(newStatus);
            System.out.printf("Booking %s status updated to %s.%n", bookingId, newStatus);
        }, () -> System.out.printf("Booking %s not found — update skipped.%n", bookingId));
    }

    /**
     * Deletes a Booking from the repository, if it exists,else deletion is skipped
     * @param bookingId the identifier of the booking to be deleted
     */
    public void delete(String bookingId) {
        boolean removed = bookings.removeIf(b -> b.getBookingId().equalsIgnoreCase(bookingId));
        if (removed)
            System.out.printf("Booking %s deleted successfully.%n", bookingId);
        else
            System.out.printf("Booking %s not found — deletion skipped.%n", bookingId);
    }

}
