package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class that represents the individual bikes in the system
 */
public class Bike {
    
    private final BikeType bikeType;
    private Provider provider;
    private String status;
    private BigDecimal replacementCost;
    private BigDecimal dailyHireCost;
    private int age;
    private ArrayList<DateRange> unavailabilities;
    
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
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public BigDecimal getDailyHireCost() {
        return dailyHireCost;
    }

    public void setDailyHireCost(BigDecimal dailyHireCost) {
        this.dailyHireCost = dailyHireCost;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<DateRange> getUnavailabilities() {
        return unavailabilities;
    }
    /**
     * Updates the status of the bike
     * @param newStatus The status that you want to set to
     */
    public void setStatus(String newStatus) {
    }
    
    /**
     * Sets the bike as unavailable during a specific duration
     * @param duration The duration that the bike is unavailable for
     */
    public void setUnavailable(DateRange duration) {       
    }
    
    /**
     * Sets the bike as unavailable during a specific duration
     * @param duration The duration we're checking for
     * @return True if the bike is available for the location
     */
    public boolean isAvailable(DateRange duration) {
        return false;
    }

    
    
    
    

}