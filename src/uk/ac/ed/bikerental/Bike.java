package uk.ac.ed.bikerental;
/**
 * Class that represents the individual bikes in the system
 */
public class Bike {
    
    final BikeType bikeType;
    /**
     * Constructor for Bike
     * @param bikeType Takes the type of bike, this is a final attribute
     */
    Bike (BikeType bikeType) {
        this.bikeType = bikeType;
    }
    /**
     * Getter for the Bikes BikeType
     * @return A BikeType object
     */
    public BikeType getType() {
        return bikeType;
    }

}