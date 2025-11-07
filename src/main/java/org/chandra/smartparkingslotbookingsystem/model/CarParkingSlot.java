package org.chandra.smartparkingslotbookingsystem.model;

/**
 * Represents a parking slot dedicated to cars.
 * Includes optional charging station availability and maximum car length limits.
 */
public class CarParkingSlot extends ParkingSlot{
    private boolean hasChargingStation;
    private int maxLength;

    public CarParkingSlot(){
        super();
    }
    public CarParkingSlot(String id, String type, String location, double hourlyRate, boolean available, boolean hasChargingStation, int maxLength){
        super(id,type,location,hourlyRate,available);
        this.hasChargingStation=hasChargingStation;
        this.maxLength=maxLength;
    }

    public boolean hasChargingStation() {return hasChargingStation;}
    public void setHasChargingStation(boolean hasChargingStation) {this.hasChargingStation = hasChargingStation;}
    public int getMaxLength() {return maxLength;}
    public void setMaxLength(int maxLength) {this.maxLength = maxLength;}

    @Override
    public String toString() {
        return String.format("%s | Charging: %s | Max Length: %dft",
                super.toString(),
                hasChargingStation ? "Yes" : "No",
                maxLength);
    }
}
