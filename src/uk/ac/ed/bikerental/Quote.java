package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;


/**
 * 
 * This class encapsulates information about quotes
 *
 */
public class Quote {

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
        this.bikes = (ArrayList<Bike>) bikes;
        this.provider = provider;
        this.duration = duration;
        this.cost = cost;
        this.deposit = deposit;
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
}
