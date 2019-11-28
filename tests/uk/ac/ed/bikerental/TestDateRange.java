package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3, dateRange4, dateRange5;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        this.dateRange4 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        this.dateRange5 = new DateRange(LocalDate.of(2019, 1, 9),
                LocalDate.of(2019, 1, 12));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }
    //Testing the overlap
    @Test
    void testOverlapsTrue() {
        //check we can see when two date ranges overlap
        boolean overlaps = this.dateRange1.overlaps(this.dateRange2);
        assertEquals(overlaps, true);
    }
 
    @Test
    void testOverlapsFalse() {
        //check we can see when two date ranges  don't overlap
        boolean overlaps = this.dateRange1.overlaps(this.dateRange3);
        assertEquals(overlaps, false);
    }
    //Testing equals
    @Test
    void testEquals() {
        assertTrue(this.dateRange3.equals(this.dateRange4));
    }
    
    @Test
    void testNotEqual() {
        assertFalse(this.dateRange1.equals(dateRange4));
    }
    
    //Testing toDays()
    @Test
    void testToDays3() {
        assertEquals(3, this.dateRange1.toDays());
    }
    
    @Test
    void testToDaysMany() {
        assertEquals(18, this.dateRange2.toDays());
    }

}
