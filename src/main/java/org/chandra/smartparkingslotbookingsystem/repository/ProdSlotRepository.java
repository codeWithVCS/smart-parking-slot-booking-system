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
 * Production profile implementation of {@link SlotRepository}.
 * <p>
 * Loads a larger, more realistic set of {@link ParkingSlot} objects to
 * simulate production-scale operations.
 * </p>
 *
 * <p><strong>Profile:</strong> prod</p>
 */
@Repository
@Profile("prod")
public class ProdSlotRepository implements SlotRepository {
    private final List<ParkingSlot> slots = new ArrayList<>();
    // Note: Not thread-safe. For demo purposes only;
    // consider concurrent structures or persistence in production.
    @PostConstruct
    public void init() {
        System.out.println("🚀 ProdSlotRepository initialized — loading production dataset...");

        // Car slots
        slots.add(new CarParkingSlot("C-301", "Car", "P1", 100.0, true, true, 15));
        slots.add(new CarParkingSlot("C-302", "Car", "P1", 120.0, false, true, 15));
        slots.add(new CarParkingSlot("C-303", "Car", "P2", 110.0, true, false, 15));
        slots.add(new CarParkingSlot("C-304", "Car", "P2", 95.0, true, false, 14));
        slots.add(new CarParkingSlot("C-305", "Car", "P3", 130.0, false, true, 16));
        slots.add(new CarParkingSlot("C-306", "Car", "P3", 125.0, true, true, 16));
        slots.add(new CarParkingSlot("C-307", "Car", "P3", 90.0, true, false, 15));

        // Bike slots
        slots.add(new BikeParkingSlot("B-401", "Bike", "Q1", 30.0, true, true, true));
        slots.add(new BikeParkingSlot("B-402", "Bike", "Q1", 28.0, false, true, false));
        slots.add(new BikeParkingSlot("B-403", "Bike", "Q2", 25.0, true, false, true));
        slots.add(new BikeParkingSlot("B-404", "Bike", "Q2", 27.0, true, false, true));
        slots.add(new BikeParkingSlot("B-405", "Bike", "Q3", 29.0, true, true, false));

        System.out.printf("Loaded %d production slots.%n", slots.size());
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("🛑 ProdSlotRepository shutting down — context closing...");
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
