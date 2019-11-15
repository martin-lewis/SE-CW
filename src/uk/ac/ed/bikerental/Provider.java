package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Provider {
    private String name;
    private String email;
    private Location address;
    private BigDecimal depositRAte;
    private ArrayList<Bike> bikeList;
    private String openingTimes;
    private String phoneNumber;
    private ArrayList<Provider> partners;
    private ArrayList<Booking> bookings;
    private PricingPolicy pricingPolicy;
    private ValuationPolicy depositPolicy;       
    
    public ArrayList<Bike> getAvailableBikes(int noBikes, ArrayList<String> types, DateRange duration ){
        return null;
    }
    
    public void setBikesUnavailable(ArrayList<Bike> bikes, DateRange duration) {
        
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

    public BigDecimal getDepositRAte() {
        return depositRAte;
    }

    public void setDepositRAte(BigDecimal depositRAte) {
        this.depositRAte = depositRAte;
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

    public ArrayList<Provider> getPartners() {
        return partners;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setPricingPolicy(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }

    public void setDepositPolicy(ValuationPolicy depositPolicy) {
        this.depositPolicy = depositPolicy;
    }
    
    
}
