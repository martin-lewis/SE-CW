package uk.ac.ed.bikerental;

public class Bike {
    
    final BikeType bikeType;
    
    Bike (BikeType bikeType) {
        this.bikeType = bikeType;
    }
    
    public BikeType getBikeType() {
        return bikeType;
    }



    public BikeType getType() {
        // TODO: Implement Bike.getType
        assert false;
        return null;
    }
}