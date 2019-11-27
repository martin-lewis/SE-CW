package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class that represents the individual bikes in the system
 */

public class Bike implements Comparable {
    
    public static int counter = 0; //Counter is used to make the unique ids, stays as static
    
    private final int uniqueID; //This is needed to do the comparisons
    private final BikeType bikeType;
    private Provider provider;
    private String status;
    // TODO remove variables that are actually stored within bikeType
    private BigDecimal replacementCost;
    private BigDecimal dailyHireCost;
    private int age;
    private ArrayList<DateRange> unavailabilities;
    private ArrayList<String> possibleStates;
    
    /**
     * Constructor for Bike
     * @param bikeType Takes the type of bike, this is a final attribute
     */
    public Bike (BikeType bikeType) {
        this.uniqueID = counter; //Sets the unique id
        counter += 1; //Increments the counter
        this.bikeType = bikeType;
        this.age = 0;
        this.status = "Available";
        this.unavailabilities = new ArrayList<>();
        this.possibleStates = new ArrayList<String>();
        this.possibleStates.add("Available");
        this.possibleStates.add("Unavailable");
    }
    
    public boolean equals(Bike bike) {
        return this.uniqueID == bike.uniqueID;
    }
    
    public String getGenericType() {
        return bikeType.getGenericType();
    }
    
    /**
     * Getter for the Bikes BikeType
     * @return A BikeType object
     */
    public BikeType getType() {
        return bikeType;
    }
    public BigDecimal getReplacementCost() {
        return bikeType.getReplacementValue();
    }
    
    // TODO remove 
    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public BigDecimal getDailyHireCost() {
        return bikeType.getDailyPrice();
    }
    
    // TODO remove
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
        if (this.possibleStates.contains(newStatus)) {
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException("Not valid State");
        }
    }
    
    /**
     * Sets the bike as unavailable during a specific duration, assumes it does not overlap with existing
     * @param duration The duration that the bike is unavailable for
     */
    public void setUnavailable(DateRange duration) {
        this.unavailabilities.add(duration);
    }
    
    /**
     * Sets the bike as unavailable during a specific duration
     * @param duration The duration we're checking for
     * @return True if the bike is available for the location
     */
    public boolean isAvailable(DateRange duration) {
        boolean available = true;
        for (DateRange date: this.unavailabilities) {
            if (date.overlaps(duration)) {
                available = false;
                break;
            }
        }
        return available;
    }
    /**
     * Method required to make Bike comparable
     */
    @Override
    public int compareTo(Object arg0) {
        Bike temp = (Bike) arg0;
        return this.uniqueID - temp.uniqueID;
    }

    
    
    
    

}