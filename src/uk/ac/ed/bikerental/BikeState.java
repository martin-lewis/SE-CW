package uk.ac.ed.bikerental;

// Stores possible states that a bike can have, each state has JavaDoc describing when that state should exist
public enum BikeState {
    /**
     * The bike is at its original provider, available to be taken out
     */
    AVAILABLE,
    /**
     * The bike is not available
     */
    UNAVAILABLE;
}
