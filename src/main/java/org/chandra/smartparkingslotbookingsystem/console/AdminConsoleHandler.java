package org.chandra.smartparkingslotbookingsystem.console;

import org.chandra.smartparkingslotbookingsystem.model.*;
import org.chandra.smartparkingslotbookingsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all administrative console operations:
 * managing parking slots, viewing bookings, and force-cancelling bookings.
 */
@Component
public class AdminConsoleHandler {

    private final AdminService adminService;

    @Autowired
    public AdminConsoleHandler(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Starts the interactive admin mode.
     *
     * @param scanner shared Scanner instance from main runner
     */
    public void startAdminFlow(Scanner scanner) {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View All Slots");
            System.out.println("2. Add New Slot");
            System.out.println("3. Update Slot");
            System.out.println("4. Delete Slot");
            System.out.println("5. View All Bookings");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit to Main Menu");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAllSlots();
                case 2 -> addNewSlot(scanner);
                case 3 -> updateSlot(scanner);
                case 4 -> deleteSlot(scanner);
                case 5 -> viewAllBookings();
                case 6 -> cancelBooking(scanner);
                case 7 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Displays all parking slots.
     */
    private void viewAllSlots() {
        List<ParkingSlot> slots = adminService.getAllSlots();
        if (slots.isEmpty()) {
            System.out.println("No parking slots found.");
        } else {
            System.out.println("\nAll Parking Slots:");
            slots.forEach(System.out::println);
        }
    }

    /**
     * Allows the admin to create a new parking slot.
     */
    private void addNewSlot(Scanner scanner) {
        System.out.println("\n===== ADD NEW SLOT =====");
        System.out.print("Enter Slot ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Slot Type (Car/Bike): ");
        String type = scanner.nextLine();

        System.out.print("Enter Location Code (e.g., A1, B2): ");
        String location = scanner.nextLine();

        System.out.print("Enter Hourly Rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();

        boolean available = true; // new slots start available by default

        if (type.equalsIgnoreCase("Car")) {
            System.out.print("Has charging station? (true/false): ");
            boolean charging = scanner.nextBoolean();
            System.out.print("Enter max vehicle length (in feet): ");
            int maxLength = scanner.nextInt();
            scanner.nextLine();

            adminService.createSlot(new CarParkingSlot(id, type, location, rate, available, charging, maxLength));

        } else if (type.equalsIgnoreCase("Bike")) {
            System.out.print("Has helmet lock? (true/false): ");
            boolean helmetLock = scanner.nextBoolean();
            System.out.print("Has charging station? (true/false): ");
            boolean charging = scanner.nextBoolean();
            scanner.nextLine();

            adminService.createSlot(new BikeParkingSlot(id, type, location, rate, available, charging, helmetLock));

        } else {
            System.out.println("Invalid slot type.");
        }
    }

    /**
     * Updates a parking slot if it exists.
     */
    private void updateSlot(Scanner scanner) {
        System.out.println("\n===== UPDATE SLOT =====");
        System.out.print("Enter existing Slot ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter new Hourly Rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Mark as available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        // Update basic info (type and location remain unchanged for simplicity)
        adminService.getAllSlots().stream()
                .filter(slot -> slot.getId().equalsIgnoreCase(id))
                .findFirst()
                .ifPresentOrElse(slot -> {
                    slot.setHourlyRate(rate);
                    slot.setAvailable(available);
                    adminService.updateSlot(slot);
                }, () -> System.out.println("Slot not found."));
    }

    /**
     * Deletes a slot by its ID.
     */
    private void deleteSlot(Scanner scanner) {
        System.out.println("\n===== DELETE SLOT =====");
        System.out.print("Enter Slot ID to delete: ");
        String id = scanner.nextLine();

        adminService.getAllSlots().stream()
                .filter(slot -> slot.getId().equalsIgnoreCase(id))
                .findFirst()
                .ifPresentOrElse(adminService::deleteSlot,
                        () -> System.out.println("Slot not found."));
    }

    /**
     * Displays all bookings in the system.
     */
    private void viewAllBookings() {
        List<Booking> bookings = adminService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("\nAll Bookings:");
            bookings.forEach(System.out::println);
        }
    }

    /**
     * Allows the admin to cancel any booking.
     */
    private void cancelBooking(Scanner scanner) {
        System.out.print("\nEnter Booking ID to cancel: ");
        String bookingId = scanner.nextLine();
        adminService.cancelBookingAsAdmin(bookingId);
    }
}
