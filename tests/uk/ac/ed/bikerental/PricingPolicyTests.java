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
    

    @BeforeEach
    void setUp() throws Exception {
        testPolicy = new APricingPolicy();
        
        
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,5)); 
        System.out.println(dates.toDays()); //toDays() seems to give number of days -1? Possibly doesn't consider the end or the start as a day
        
        BigDecimal testPrice = new BigDecimal(50);
        BikeType testType = new BikeType(testPrice);
        testBikes = new ArrayList<Bike>();
        for (int i = 0; i < 10; i++) {
            testBikes.add(new Bike(testType));
        }
    }
    
    // TODO: Write tests for pricing policies
    @Test
    public void noDiscount() {
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        System.out.print(calcVal);
        BigDecimal actualVal = new BigDecimal(2500);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
}