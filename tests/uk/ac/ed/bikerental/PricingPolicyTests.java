package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.*;


public class PricingPolicyTests {
    // You can add attributes here

    @BeforeEach
    void setUp() throws Exception {
        BigDecimal testPrice = new BigDecimal(50);
        BikeType testType = new BikeType(testPrice);
    }
    
    // TODO: Write tests for pricing policies
    @Test
    public void test1() {
        assertEquals(true, true);
    }
}