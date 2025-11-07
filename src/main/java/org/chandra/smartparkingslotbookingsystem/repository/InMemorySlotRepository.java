package org.chandra.smartparkingslotbookingsystem.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.chandra.smartparkingslotbookingsystem.model.BikeParkingSlot;
import org.chandra.smartparkingslotbookingsystem.model.CarParkingSlot;
import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Development profile implementation of {@link SlotRepository}.
 * <p>
 * This repository stores {@link ParkingSlot} objects in memory for development and testing.
 * It preloads a few sample {@link CarParkingSlot} and {@link BikeParkingSlot} entries
 * when the Spring context is initialized and clears them upon shutdown.
 * </p>
 *
 * <p><strong>Profile:</strong> dev</p>
 */

@Repository
@Profile("dev")
public class InMemorySlotRepository implements SlotRepository {
    private List<ParkingSlot> slots = new ArrayList<>();

    /**
     * Initializes the in-memory repository with sample parking slots
     * after the Spring context is fully loaded.
     */
    @PostConstruct
    public void init() {
        System.out.println("✅ InMemorySlotRepository (dev) initialized — loading sample parking slots...");

        slots.add(new CarParkingSlot("C-101", "Car", "A1", 50.0, true, true, 15));
        slots.add(new CarParkingSlot("C-102", "Car", "A2", 45.0, true, false, 15));
        slots.add(new BikeParkingSlot("B-201", "Bike", "B1", 20.0, true, true, true));
        slots.add(new BikeParkingSlot("B-202", "Bike", "B2", 25.0, false, false, true));

        System.out.printf("Loaded %d sample slots.%n", slots.size());
    }

    /**
     * Clears in-memory data before the Spring context is destroyed.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("InMemorySlotRepository (dev) shutting down — clearing in-memory data...");
        slots.clear();
    }

    /**
     * Retrieves all parking slots currently available in the repository.
     *
     * @return a new {@link List} containing all stored {@link ParkingSlot} objects
     */
    @Override
    public List<ParkingSlot> findAll() {
        return new ArrayList<>(slots);
    }

    /**
     * Finds a parking slot by its unique ID.
     *
     * @param slotId the identifier of the slot to search for
     * @return an {@link Optional} containing the {@link ParkingSlot} if found, otherwise empty
     */
    @Override
    public Optional<ParkingSlot> findById(String slotId) {
        return slots.stream()
                .filter(slot -> slot.getId().equalsIgnoreCase(slotId))
                .findFirst();
    }

    /**
     * Adds a new parking slot to the repository.
     *
     * @param slot the {@link ParkingSlot} instance to be added
     */
    @Override
    public void save(ParkingSlot slot) {
        slots.add(slot);
        System.out.printf("Slot %s added successfully.%n", slot.getId());
    }

    /**
     * Updates an existing parking slot if it exists.
     * <p>
     * If no matching slot is found, the update operation is skipped gracefully.
     * </p>
     *
     * @param slot the updated {@link ParkingSlot} instance
     */
    @Override
    public void update(ParkingSlot slot) {
        findById(slot.getId()).ifPresentOrElse(existing -> {
            slots.remove(existing);
            slots.add(slot);
            System.out.printf("Slot %s updated successfully.%n", slot.getId());
        }, () -> System.out.printf("Slot %s not found — update skipped.%n", slot.getId()));
    }

    /**
     * Deletes an existing parking slot from the repository.
     * <p>
     * If the specified slot does not exist, a warning message is logged.
     * </p>
     *
     * @param slot the {@link ParkingSlot} to be deleted
     */
    @Override
    public void delete(ParkingSlot slot) {
        boolean removed = slots.removeIf(s -> s.getId().equalsIgnoreCase(slot.getId()));
        if (removed)
            System.out.printf("Slot %s deleted successfully.%n", slot.getId());
        else
            System.out.printf("Slot %s not found — deletion skipped.%n", slot.getId());
    }

    /**
     * Retrieves all available parking slots of a specified type (e.g., "Car", "Bike").
     *
     * @param type the type of parking slot to filter by
     * @return a {@link List} of available {@link ParkingSlot} objects matching the given type
     */
    @Override
    public List<ParkingSlot> findAvailableByType(String type) {
        return slots.stream()
                .filter(slot -> slot.getType().equalsIgnoreCase(type) && slot.isAvailable())
                .collect(Collectors.toList());
    }
}
