package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that contains information about individual booking and
 * allows updates to be made to it
 */
public class Booking implements Deliverable{
    public static int counter;
    
    public final int uniqueID;
    private final Customer customer;
    private final Provider provider;
    private final ArrayList<Bike> bikes;
    private BookingState state;
    private final Location address;
    private final DateRange duration;
    private final BigDecimal cost;
    private final BigDecimal deposit;
    
    /**
     * The main constructor for Booking which takes a quote and turns it
     * into a booking
     * @param quote A Quote object which contains many of the needed details
     * @param customer A Customer object containing the customer details
     * @param address The address that the bike should be delivered to
     * if this is null then delivery is not needed
     */
    public Booking(Quote quote, Customer customer, Location address) {
        this.uniqueID = counter;
        counter++;
        this.customer = customer;
        this.address = address;
        this.state = BookingState.BOOKED; 
        this.provider = quote.getProvider();
        this.bikes = quote.getBikes();
        this.duration = quote.getDuration();
        this.cost = quote.getCost();
        this.deposit = quote.getDeposit();
        
    }
    
    /**
     * A protected method designed to be used for testing purposes
     * @param customer A Customer object representing the customer
     * @param provider A Provider object which is the owner of the Bike Objects
     * @param bikes A collection of the Bike objects
     * @param state A string that represents the state, no validation here assumes correct
     * @param address A Location class that is the delivery address, null if no delivery required
     * @param duration A DateRange that covers the period of the bike hire
     * @param cost The total cost of hiring the bikes
     * @param deposit The deposit required
     */
    protected Booking(Customer customer, Provider provider, ArrayList<Bike> bikes, BookingState state, Location address,
            DateRange duration, BigDecimal cost, BigDecimal deposit) {
        this.uniqueID = counter;
        counter++;
        this.customer = customer;
        this.provider = provider;
        this.bikes = bikes;
        this.state = state;
        this.address = address;
        this.duration = duration;
        this.cost = cost;
        this.deposit = deposit;
        
    }


    /**
     * Allows for the status of the bike to be updated,
     * includes validation to ensure it is a valid state. Valid states are:
     * AVAILABLE, UNAVAILABLE
     * @param status A string of the status
     */
    public void updateStatus(BookingState status) {
        this.state = status;
    }
    /**
     * Method called when the bikes are returned to the original provider
     * @return A BigDecimal containing the deposit to return to the customer
     */
    public BigDecimal providerReturn() {
        updateStatus(BookingState.RETURNED);
        for(Bike bike: bikes) {
            bike.updateStatus(BikeState.AVAILABLE);
        }
        return this.deposit;
    }
    /**
     * Method called when the bikes are returned to one of the providers partners
     * @return A BigDecimal containing the deposit the partner should return to the customer
     */
    public BigDecimal partnerReturn() {
        updateStatus(BookingState.AWAITING_PROVIDER_RETURN_PICKUP);
        return this.deposit;
    }
    
    public Location getProviderAddress() {
        return provider.getAddress();
    }
    
    public LocalDate getEndDate() {
        return duration.getEnd();
    }
    
    public String getProviderEmail() {
        return provider.getEmail();
    }

    // Getters and setters
    public BookingState getState() {
        return state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public Location getAddress() {
        return address;
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

    @Override
    public void onPickup() {
        if(this.state == BookingState.AWAITING_PROVIDER_RETURN_PICKUP) {
            this.updateStatus(BookingState.BEINGDELIVEREDPROVIDER);
        }
        else {
            this.updateStatus(BookingState.BEINGDELIVEREDCUSTOMER);
            for(Bike bike: bikes) {
                bike.updateStatus(BikeState.UNAVAILABLE);
            }
            
        }
    }

    @Override
    public void onDropoff() {
        if(this.state == BookingState.BEINGDELIVEREDCUSTOMER) {
            this.updateStatus(BookingState.WITHCUSTOMER);
        }
        else {
            this.updateStatus(BookingState.RETURNED);
        }
    }
    
    

}
