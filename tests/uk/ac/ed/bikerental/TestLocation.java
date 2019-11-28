package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
    }
    
    // TODO: put some tests here
    
    @Test
    public void isNearTrue() { //Tests the isNear() function with some basic cases
        Location a1 = new Location("EH12 5JH", "an address");
        Location a2 = new Location("EH14 H7Y", "some address");
        assertTrue(a1.isNearTo(a2));
    }
    @Test
    public void testToUpper() { //tests that isNear still works with inconsistent capitalisation
        Location a1 = new Location("eH12 5JH", "an address");
        Location a2 = new Location("Eh14 H7Y", "some address");
        assertTrue(a1.isNearTo(a2));
    }
    @Test
    public void testOneCharacter() { //tests postcodes with a one character region code
        Location a1 = new Location("e12 5JH", "an address");
        Location a2 = new Location("E14 H7Y", "some address");
        assertTrue(a1.isNearTo(a2));
    }
    
    @Test
    public void isNearFalse() { //Tests isNear() with values that should return false
        Location a1 = new Location("NG12 5JH", "an address");
        Location a2 = new Location("EH14 H7Y", "some address");
        assertFalse(a1.isNearTo(a2));
    }
}
