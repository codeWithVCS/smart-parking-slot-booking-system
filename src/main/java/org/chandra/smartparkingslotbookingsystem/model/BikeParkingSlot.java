package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Represents a parking slot specifically for two-wheelers.
 * Includes details like helmet lock and charging station availability .
 */
public class BikeParkingSlot extends ParkingSlot{
    private boolean hasHelmetLock;
    private boolean hasChargingStation;

    public BikeParkingSlot(){}

    public BikeParkingSlot(String id, String type, String location, double hourlyRate, boolean available, boolean hasChargingStation,  boolean hasHelmetLock) {
        super(id, type, location, hourlyRate, available);
        this.hasChargingStation = hasChargingStation;
        this.hasHelmetLock = hasHelmetLock;
    }

    public boolean hasHelmetLock() {return hasHelmetLock;}
    public void setHasHelmetLock(boolean hasHelmetLock) {this.hasHelmetLock = hasHelmetLock;}
    public boolean hasChargingStation() {return hasChargingStation;}
    public void setHasChargingStation(boolean hasChargingStation) {this.hasChargingStation = hasChargingStation;}

    @Override
    public String toString() {
        return String.format("%s | Helmet Lock: %s | Charging: %s",
                super.toString(),
                hasHelmetLock ? "Yes" : "No",
                hasChargingStation ? "Yes" : "No");
    }
}
