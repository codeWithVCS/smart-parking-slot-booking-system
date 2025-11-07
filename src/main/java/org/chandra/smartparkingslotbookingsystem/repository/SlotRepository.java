package org.chandra.smartparkingslotbookingsystem.repository;

import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;

import java.util.List;
import java.util.Optional;

/**
 * Common contract for managing parking slots in the system.
 * Implementations may vary by environment (dev, prod).
 */
public interface SlotRepository {
    List<ParkingSlot> findAll();
    Optional<ParkingSlot> findById(String slotId);
    void save(ParkingSlot slot);
    void update(ParkingSlot slot);
    void delete(ParkingSlot slot);
    List<ParkingSlot> findAvailableByType(String type);
}
