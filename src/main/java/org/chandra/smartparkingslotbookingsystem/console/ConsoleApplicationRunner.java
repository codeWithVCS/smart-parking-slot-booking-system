package org.chandra.smartparkingslotbookingsystem.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final UserConsoleHandler userConsoleHandler;
    private final AdminConsoleHandler adminConsoleHandler;

    @Autowired
    public ConsoleApplicationRunner(UserConsoleHandler userConsoleHandler, AdminConsoleHandler adminConsoleHandler) {
        this.userConsoleHandler = userConsoleHandler;
        this.adminConsoleHandler = adminConsoleHandler;
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println(" SMART PARKING SLOT BOOKING SYSTEM ");
        System.out.println("======================================");

        while (true) {
            System.out.println("\nSelect mode:");
            System.out.println("1. User Mode");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Entering User Mode ---");
                    userConsoleHandler.startUserFlow(scanner);
                }
                case 2 -> {
                    System.out.println("\n--- Entering Admin Mode ---");
                    adminConsoleHandler.startAdminFlow(scanner);
                }
                case 3 -> {
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
