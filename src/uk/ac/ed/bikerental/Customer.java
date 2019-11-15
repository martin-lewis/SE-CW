package uk.ac.ed.bikerental;

import java.util.ArrayList;
/**
 * Models a customer within the system
 */
public class Customer {
    
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Location address;
    /** 
     * ArrayList of the bookings associated with this customer
     */
    private ArrayList<Booking> bookings;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Location getAddress() {
        return address;
    }
    public void setAddress(Location address) {
        this.address = address;
    }
    public ArrayList<Booking> getBookings() {
        return bookings;
    } 
    
}
