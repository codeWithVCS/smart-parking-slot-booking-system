package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing parking slots.
 * Provides methods for viewing, filtering, and updating slot availability.
 */
public interface SlotService {
    List<ParkingSlot> getAllSlots();
    List<ParkingSlot> getAvailableSlotsByType(String type);
    Optional<ParkingSlot> getSlotById(String slotId);
    void markSlotAsBooked(String slotId);
    void markSlotAsAvailable(String slotId);
}
