package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.*;


public class PricingPolicyTests {
    // You can add attributes here
    
    private APricingPolicy testPolicy;
    private ArrayList<Bike> testBikes;
    private DateRange dates;
    BigDecimal actualVal;
    

    @BeforeEach
    void setUp() throws Exception {
        testPolicy = new APricingPolicy();       
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,5));       
        BigDecimal testPrice = new BigDecimal(50);
        BikeType testType = new BikeType(testPrice);
        testBikes = new ArrayList<Bike>();
        for (int i = 0; i < 10; i++) {
            testBikes.add(new Bike(testType));
        }
        actualVal = new BigDecimal(2500);
    }
    
    @Test
    public void noDiscount() {
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
    @Test
    public void noBikes() {     // Tests for an empty arrayList
        testBikes = new ArrayList<Bike>();
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        actualVal = new BigDecimal(0);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
    @Test
    public void negativeDateRange() {   // Tests for when the starting date comes after the ending date
        
    }
    
    
}