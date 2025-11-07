package org.chandra.smartparkingslotbookingsystem.service;

import org.chandra.smartparkingslotbookingsystem.model.ParkingSlot;
import org.chandra.smartparkingslotbookingsystem.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link SlotService} responsible for managing parking slots
 * Uses the active profile's {@link SlotRepository} for persistence operations.
 */
@Service
public class SlotServiceImpl implements SlotService{

    private final SlotRepository slotRepository;

    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    /**
     * Service method which retrieves a new {@link List} of all the slots
     * @return a new {@link List} of all the {@link ParkingSlot}
     */
    @Override
    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    /**
     * Service method which retrieves a list of available slots by their type
     * @param type the type of the {@link ParkingSlot} to search for
     * @return a new {@link List} of {@link ParkingSlot} of the specified type
     */
    @Override
    public List<ParkingSlot> getAvailableSlotsByType(String type) {
        return slotRepository.findAvailableByType(type);
    }

    /**
     * Service method which returns a slot based on the specified slot Id
     * @param slotId the unique identifier of the slot to be searched for
     * @return an {@link Optional} {@link ParkingSlot} if exists or gets skipped
     */
    @Override
    public Optional<ParkingSlot> getSlotById(String slotId) {
        return slotRepository.findById(slotId);
    }

    /**
     * Service method to mark as slot as booked based on the slot Id specified
     * @param slotId unique identifier of the slot which is to be marked as booked
     */
    @Override
    public void markSlotAsBooked(String slotId) {
        slotRepository.findById(slotId).ifPresentOrElse(slot -> {
            slot.markBooked();
            slotRepository.update(slot);
            System.out.printf("Slot %s successfully marked as booked.%n", slotId);
        }, () -> System.out.printf("Slot %s not found — cannot mark as booked.%n", slotId));
    }

    /**
     * Service method to mark a slot as available based on the slot Id specified
     * @param slotId unique identifier of the slot which is to be marked as available
     */
    @Override
    public void markSlotAsAvailable(String slotId) {
        slotRepository.findById(slotId).ifPresentOrElse(slot -> {
            slot.markAvailable();
            slotRepository.update(slot);
            System.out.printf("Slot %s successfully marked as available.%n", slotId);
        }, () -> System.out.printf("Slot %s not found — cannot mark as available.%n", slotId));
    }
}
