package uk.ac.ed.bikerental;

public class Bike {
    
    final BikeType bikeType;
    
    Bike (BikeType bikeType) {
        this.bikeType = bikeType;
    }
    
    public BikeType getType() {
        return bikeType;
    }

}