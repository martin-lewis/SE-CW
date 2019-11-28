package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.*;


public class PricingPolicyTests {
    // You can add attributes here
    
    private MultiDayPricingPolicy testPolicy;
    private ArrayList<Bike> testBikes;
    private DateRange dates;
    BigDecimal actualVal;
    

    @BeforeEach
    void setUp() throws Exception {
        testPolicy = new MultiDayPricingPolicy(); //Creates our policy       
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,5)); //Test dates   
        BigDecimal testPrice = new BigDecimal(50);
        BikeType testType = new BikeType(testPrice, testPrice, "testGenericType");
        testBikes = new ArrayList<Bike>();
        for (int i = 0; i < 10; i++) { //Creates a number of bikes to test
            testBikes.add(new Bike(testType));
        }
        actualVal = new BigDecimal(2500);
    }
    
    @Test
    public void noDiscount() { //Tests the policy when there are no tests yet added only the default
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
    public void zeroDateRange() {   //Tests when start date is same as end date
        dates = new DateRange(LocalDate.of(2019,10,5),LocalDate.of(2019,10,5));
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        actualVal = new BigDecimal(500);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
    @Test
    public void negativeDateRange() {   // Tests for when the starting date comes after the ending date
        dates = new DateRange(LocalDate.of(2019,10,5),LocalDate.of(2019,10,4));
        assertThrows(AssertionError.class, () -> testPolicy.calculatePrice(testBikes, dates));
    }
    @Test
    public void addingDiscount() {   // Tests when adding a new discount
        testPolicy.setDiscount((long)1, 10);
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        BigDecimal actualVal = new BigDecimal(2250);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
        
    }
    @Test
    public void multiMonthDateRange() { //Tests that it works when there are different months involved
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,11,1));
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        actualVal = new BigDecimal(16000);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
    @Test
    public void multipleDiscounts() { //Checks with multiple levels of discounts
        testPolicy.setDiscount((long) 4, 10); //Sets two discounts
        testPolicy.setDiscount((long) 6, 15);
        //Testing
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,2)); //Sets the dates <4
        BigDecimal calcVal = testPolicy.calculatePrice(testBikes, dates);
        BigDecimal actualVal = new BigDecimal(1000);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
        
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,5)); //Sets the dates 5
        calcVal = testPolicy.calculatePrice(testBikes, dates);
        actualVal = new BigDecimal(2250);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
        
        dates = new DateRange(LocalDate.of(2019,10,1),LocalDate.of(2019,10,10)); //Sets the dates >6
        calcVal = testPolicy.calculatePrice(testBikes, dates);
        actualVal = new BigDecimal(4250);
        assertEquals(calcVal.stripTrailingZeros(), actualVal.stripTrailingZeros());
    }
    @Test
    public void nullBikes() { //Tests when the list of bikes is null
        testBikes = null;
        assertThrows(AssertionError.class, () -> testPolicy.calculatePrice(testBikes, dates));
    }
    
}