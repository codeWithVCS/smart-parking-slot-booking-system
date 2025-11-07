package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Base abstract class representing a generic parking slot
 * All specific slot types (Car, Bike, Truck, etc.) will extend this class
 */
public abstract class ParkingSlot {
    private String id;
    private String type;
    private String location;
    private double hourlyRate;
    private boolean available;

    protected ParkingSlot() {}

    public ParkingSlot(String id, String type, String location, double hourlyRate, boolean available) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.hourlyRate = hourlyRate;
        this.available = available;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public double getHourlyRate() {return hourlyRate;}
    public void setHourlyRate(double hourlyRate) {this.hourlyRate = hourlyRate;}
    public boolean isAvailable() {return available;}
    public void setAvailable(boolean available) {this.available = available;}

    @Override
    public String toString() {
        return String.format("[%s] Type: %s | Location: %s | Rate: %.2f/hr | Available: %s",
                id, type, location, hourlyRate, available ? "Yes" : "No");
    }

    /**
     * Marks a slot as booked
     */
    public  void markBooked(){this.available=false;}

    /**
     * Marks a slot as available
     */
    public void markAvailable(){this.available=true;}
}

