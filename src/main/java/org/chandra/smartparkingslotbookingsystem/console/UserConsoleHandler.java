package org.chandra.smartparkingslotbookingsystem.console;

import org.chandra.smartparkingslotbookingsystem.model.Booking;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.chandra.smartparkingslotbookingsystem.service.BookingService;
import org.chandra.smartparkingslotbookingsystem.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all user-side console operations:
 * viewing available slots, making bookings,
 * viewing existing bookings, and cancelling bookings.
 */
@Component
public class UserConsoleHandler {

    private final SlotService slotService;
    private final BookingService bookingService;

    @Autowired
    public UserConsoleHandler(SlotService slotService, BookingService bookingService) {
        this.slotService = slotService;
        this.bookingService = bookingService;
    }

    /**
     * Starts the interactive user mode.
     *
     * @param scanner shared Scanner instance from main runner
     */
    public void startUserFlow(Scanner scanner) {
        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book a Slot");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAvailableSlots(scanner);
                case 2 -> makeBooking(scanner);
                case 3 -> viewMyBookings(scanner);
                case 4 -> cancelBooking(scanner);
                case 5 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Displays available parking slots based on type.
     */
    private void viewAvailableSlots(Scanner scanner) {
        System.out.print("Enter slot type (Car/Bike): ");
        String type = scanner.nextLine();
        List<ParkingSlot> availableSlots = slotService.getAvailableSlotsByType(type);
        if (availableSlots.isEmpty()) {
            System.out.println("No available " + type + " slots found.");
        } else {
            System.out.println("\nAvailable Slots:");
            availableSlots.forEach(System.out::println);
        }
    }

    /**
     * Facilitates user booking creation with
     * friendly date and time entry prompts.
     */
    private void makeBooking(Scanner scanner) {
        System.out.println("\n===== CREATE BOOKING =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter your vehicle registration number: ");
        String vehicleNumber = scanner.nextLine();

        System.out.print("Enter slot ID to book: ");
        String slotId = scanner.nextLine();

        // Define formatters
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            // Booking start time
            System.out.print("Enter booking start date (dd-MM-yyyy): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Enter booking start time (HH:mm): ");
            LocalTime startTime = LocalTime.parse(scanner.nextLine(), timeFormatter);

            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

            // Booking end time
            System.out.print("Enter booking end date (dd-MM-yyyy): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

            System.out.print("Enter booking end time (HH:mm): ");
            LocalTime endTime = LocalTime.parse(scanner.nextLine(), timeFormatter);

            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            // Validate that end time is after start time
            if (!endDateTime.isAfter(startDateTime)) {
                System.out.println("End time must be after start time. Booking not created.");
                return;
            }

            bookingService.createBooking(name, email, phone, vehicleNumber, slotId, startDateTime, endDateTime);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use 'dd-MM-yyyy' for date and 'HH:mm' for time.");
        }
    }

    /**
     * Displays all bookings associated with a user's phone number.
     */
    private void viewMyBookings(Scanner scanner) {
        System.out.print("\nEnter your phone number: ");
        String phone = scanner.nextLine();
        List<Booking> bookings = bookingService.getBookingsByUserPhone(phone);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for phone: " + phone);
        } else {
            System.out.println("\nYour Bookings:");
            bookings.forEach(System.out::println);
        }
    }

    /**
     * Cancels a booking based on user-provided booking ID.
     */
    private void cancelBooking(Scanner scanner) {
        System.out.print("\nEnter booking ID to cancel: ");
        String bookingId = scanner.nextLine();
        bookingService.cancelBooking(bookingId);
    }
}
