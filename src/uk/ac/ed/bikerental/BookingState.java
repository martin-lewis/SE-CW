package uk.ac.ed.bikerental;

// Stores states that a booking can have with JavaDoc
public enum BookingState {
    /**
     * The booking exists within the system, and has not started
     */
    BOOKED,
    /**
     * The booking is being delivered to its provider from a partner that it was returned to
     */
    BEINGDELIVEREDPROVIDER,
    /**
     *   The Booking is being delivered to a customer
     */
    BEINGDELIVEREDCUSTOMER,
    /**
     * The Booking has been delivered/picked up to/by a customer
     */
    WITHCUSTOMER,
    /**
     * The Booking is awaiting pickup for delivery to its original provider
     */
    AWAITING_PROVIDER_RETURN_PICKUP,  
    /**
     * The booking is finished and has been returned to its original provider
     */
    RETURNED;
}
