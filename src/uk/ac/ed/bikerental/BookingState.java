package uk.ac.ed.bikerental;

// Stores states that a booking can have
public enum BookingState {
    BOOKED, // The booking exists within the system, and has nor started
    BEINGDELIVEREDPROVIDER, // The booking is being delivered to its provider from a partner that it was
                            //  returned to 
    BEINGDELIVEREDCUSTOMER, // The Booking is being delivered to a customer
    WITHCUSTOMER,           // The Booking has been delivered/picked up to/by a customer    
    AWAITING_PROVIDER_RETURN_PICKUP,    // The Booking is awaiting pickup for deliverty 
                                        // to its original provider
    RETURNED;               // The booking is finished and has been returned to its original provider
}
