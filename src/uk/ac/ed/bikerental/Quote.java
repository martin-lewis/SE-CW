package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;


/**
 * 
 * This class encapsulates information about quotes
 *
 */
public class Quote implements Comparable{
    public static int counter = 0; //Counter is static to assign unique id's
    
    public final int uniqueID;
    private final ArrayList<Bike> bikes;
    private final Provider provider;
    private final DateRange duration;
    private final BigDecimal cost;
    private final BigDecimal deposit;
    
    /**
     * Constructor
     * @param bikes The bikes involved in the quote
     * @param provider The provider that owns the bikes
     * @param duration The DateRange over which the bikes will be hired
     * @param cost The total cost of hiring the bikes over the duration
     * @param deposit The deposit amount
     */
    public Quote(Collection<Bike> bikes, Provider provider, DateRange duration, BigDecimal cost, BigDecimal deposit) {
        this.uniqueID = counter; //Assigns id 
        counter += 1; //Incremenets counter
        this.bikes = (ArrayList<Bike>) bikes;
        this.provider = provider;
        this.duration = duration;
        this.cost = cost;
        this.deposit = deposit;
    }
    
    /**
     * Checks equality between quote called on and another that is given
     *  equality for bike collections is done by content, but bike instances are equal
     *  if have the same reference. Provider equality is also done by reference, but 
     *  duration, cost, and deposit are checked by content.
     * @param quote The quote we check against
     * @return Ture if .equals is true for all instance variables
     */ 
    public Boolean equals(Quote quote) {
        for(Bike bike : this.bikes) {
            if(!quote.bikes.contains(bike)) {
                return false;
            }
        }
        for(Bike bike: quote.bikes) {
            if(!this.bikes.contains(bike)) {
                return false;
            }
        }
        Boolean provEquality = (this.provider == quote.provider);
        Boolean durEquality = (this.duration.equals(quote.duration));
        Boolean costEquality = (this.cost.equals(quote.cost));
        Boolean depositEquality = (this.duration.equals(quote.duration));
        if(!(provEquality && durEquality && costEquality && depositEquality)) {
            return false;
        }
        return true;
    }
    
    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public Provider getProvider() {
        return provider;
    }

    public DateRange getDuration() {
        return duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }
    /**
     * Function that in required for the class to implement Comparable.
     * It simply takes the unique ID of the object its called on and subtracts
     * the id of the object passed. This will order them with the first initialised
     * first
     */
    @Override
    public int compareTo(Object o) {
        Quote temp = (Quote) o;
        return (this.uniqueID - temp.uniqueID);
    }
}
