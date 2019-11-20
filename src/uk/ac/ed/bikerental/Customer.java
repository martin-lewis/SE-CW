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
    
    /**
     * Constructor for customer setting all information fields directly.
     * Initialises an empty list of bookings.
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param address
     */
    public Customer(String firstName, String lastName, String email, String phoneNumber, Location address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bookings = new ArrayList<Booking>();
    }
    
    /**
     * Adds a booking to the system. If you pass in a null booking there
     * will be an assertionError
     * @param booking 
     */
    public void addBooking(Booking booking){
        assert(booking != null); 
        bookings.add(booking);   
    }
    
    //Getters and setters
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
    // There is no booking remover, as we assume bookings live forever (so
    // you can see them later)
    public ArrayList<Booking> getBookings() {
        return bookings;
    } 
    
    
}
