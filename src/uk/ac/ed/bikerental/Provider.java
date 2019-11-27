package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Provider {
    private String name;
    private String email;
    private Location address;
    private BigDecimal depositRate;
    private ArrayList<Bike> bikeList;
    private String openingTimes;
    private String phoneNumber;
    private ArrayList<Provider> partners;
    private ArrayList<Booking> bookings;
    private PricingPolicy pricingPolicy;
    private ValuationPolicy ValuationPolicy;
    
    /**
     * Constructor for Provider
     * @param name Name of the shop
     * @param email Email for the shop
     * @param address Address of the shop
     * @param depositRate The deposit rate
     * @param openingTimes The opening times
     * @param phoneNumber The phone number
     */
    public Provider(String name, String email, Location address, BigDecimal depositRate,
            String openingTimes, String phoneNumber) {
        super();
        this.name = name;
        this.email = email;
        this.address = address;
        this.depositRate = depositRate;
        this.bikeList = new ArrayList<Bike>();
        this.openingTimes = openingTimes;
        this.phoneNumber = phoneNumber;
        this.partners = new ArrayList<Provider>();
        this.bookings = new ArrayList<Booking>();
        this.pricingPolicy = new DefaultPricingPolicy();
        this.ValuationPolicy = new DefaultValuationPolicy();
    }
    /**
     * Returns a list of size noBikes that fits the requirements passed, or null if not enough bikes are found
     * @param noBikes The number of bikes that are required
     * @param types An ArrayList of the types that are wanted
     * @param duration The DateRange over which the bikes are wanted
     * @return ArrayList of size noBikes is bikes are found or null if not enough bikes are available
     */
    public ArrayList<Bike> getAvailableBikes(int noBikes, ArrayList<String> types, DateRange duration ){
        ArrayList<Bike> availableBikes = new ArrayList<Bike>(); //New array list to hold available bikes
        int counter = 0;
        for (Bike bike : this.bikeList) { //Runs over all the providers bikes
            if ((types.contains(bike.getGenericType())) && (bike.isAvailable(duration)) && counter < noBikes) { //If it fits the requirements
                availableBikes.add(bike);
                counter += 1;
            }
        }
        if (availableBikes.size() == noBikes) { //If we have enough bikes
            return availableBikes; //Send the required number
        } else {
            return null; //If we don't have enough send none
        }
    }
    /**
     * Takes a collection of bikes and set the all unavailable for the given duration
     * @param bikes The Collection of bikes
     * @param duration The duration for which the bikes should be set unavailable
     */
    public void setBikesUnavailable(ArrayList<Bike> bikes, DateRange duration) {
        for (Bike bike : bikes) { //Checks that all the passed bikes belong to the provider
            assert(this.bikeList.contains(bike));
        }
        for (Bike bike : bikes) {
            bike.setUnavailable(duration);
        }
    }
    
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public String getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(String openingTimes) {
        this.openingTimes = openingTimes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Bike> getBikeList() {
        return bikeList;
    }
    /**
     * Adds a bike to the list of bikes
     * @param bike The bike to be added
     */
    public void addBike(Bike bike) {
        assert(bike != null);
        this.bikeList.add(bike);
    }
    /**
     * Removes a given bike from the list, if passed a bike not in the list will do nothing
     * @param bike The bike to remove
     */
    public void removeBike(Bike bike) {
        assert(bike != null);
        if (this.bikeList.contains(bike)) {
            this.bikeList.remove(bike);
        }
    }

    public ArrayList<Provider> getPartners() {
        return partners;
    }
    
    public void addPartner(Provider partner) {
        assert(partner != null);
        assert(!(partner.equals(this))); //Prevents a provider being its own partner
        this.partners.add(partner);
    }
    /**
     * Removes the given partner from the list of partners, if the Provider passed
     * is not in the list the method does nothing
     * @param partner The Provider to remove
     */
    public void removePartner(Provider partner) {
        assert(partner != null);
        if (this.partners.contains(partner)) {
            this.partners.remove(partner);
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    /**
     * Adds the given booking to the list of bookings
     * @param booking The booking to add
     */
    public void addBooking(Booking booking) {
        assert(booking != null);
        this.bookings.add(booking);
    }

    public void setPricingPolicy(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }

    public void setDepositPolicy(ValuationPolicy depositPolicy) {
        this.ValuationPolicy = depositPolicy;
    }
    /**
     * Sets the pricing in the pricing policy
     * @param bikeType The bike type being looked at
     * @param price The daily price to rent that bike type
     */
    public void setPricingInPolicy(BikeType bikeType, BigDecimal price) {
        this.pricingPolicy.setDailyRentalPrice(bikeType, price);
    }
    
    public BigDecimal calculateHirePrice(ArrayList<Bike> bikes, DateRange duration) {
        assert(bikes.size() > 0); //Tests that some bikes have been passed to the method
        for (Bike bike : bikes) { //Checks that all the passed bikes belong to the provider
            assert(this.bikeList.contains(bike));
        }
        return this.pricingPolicy.calculatePrice(bikes, duration);
    }
    /**
     * Method that calculates the deposit given a set of bikes, calls a set of methods on the bikes
     * @param bikes A list of bikes hopefully that belong to the provider
     * @return A big decimal with the value of the deposit
     */
    public BigDecimal calculateDeposit(ArrayList<Bike> bikes, LocalDate date) {
        assert(bikes.size() > 0); //Tests that some bikes have been passed to the method
        for (Bike bike : bikes) { //Checks that all the passed bikes belong to the provider
            assert(this.bikeList.contains(bike));
        }
        BigDecimal total = new BigDecimal(0); //Creates a total to hold the value
        for (Bike bike : bikes) { //For each bike given
            total = total.add(this.ValuationPolicy.calculateValue(bike, date)); //Uses valuation to find each deposit
        }
        return total.multiply(depositRate); //Multiplies this by the provider rate
    }
    
    
}
