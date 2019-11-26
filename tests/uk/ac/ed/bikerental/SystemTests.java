package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here
    BigDecimal bd50 = new BigDecimal(50);
    BigDecimal bd70 = new BigDecimal(70);
    BigDecimal bd5 = new BigDecimal(5);
    BigDecimal bd7 = new BigDecimal(7);
    Controller theController;
    ArrayList<Customer> customers;
    ArrayList<Provider> providers; 
    
    Bike MTB11;
    Bike MTB12;
    Bike MTB13;
    Bike MTB14;
    Bike MTB15;
    
    Bike MTB21;
    Bike MTB22;
    
    Bike RDB11;
    Bike RDB12;
    Bike RDB13;
    
    Provider provider1;
    Provider provider2;
    

    LocalDate date20thNov2019;
    LocalDate date25thNov2019;
    DateRange testDateRange;
    
    Location drBeesAddress;
    
    public boolean compareQuoteList(ArrayList<Quote> a, ArrayList<Quote> b) {
        for(Quote quote : a) {
            boolean found = false;
            for(Quote quote2 : b) {
                if(quote.equals(quote2)){
                    found = true;
                }
            }
            if(!found) {
                return false;
            }
        }
        for(Quote quote : b) {
            boolean found = false;
            for(Quote quote2 : a) {
                if(quote.equals(quote2)){
                    found = true;
                }
            }
            if(!found) {
                return false;
            }
        }
        return true;
    }
    
    public boolean compareBikeList(ArrayList<Bike> a, ArrayList<Bike> b) {
        for(Bike quote : a) {
            boolean found = false;
            for(Bike quote2 : b) {
                if(quote.equals(quote2)){
                    found = true;
                }
            }
            if(!found) {
                return false;
            }
        }
        for(Bike quote : b) {
            boolean found = false;
            for(Bike quote2 : a) {
                if(quote.equals(quote2)){
                    found = true;
                }
            }
            if(!found) {
                return false;
            }
        }
        return true;
    }
    
    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests

        date20thNov2019 = LocalDate.of(2019, 11, 20);
        date25thNov2019 = LocalDate.of(2019, 11, 25);
        testDateRange = new DateRange(date20thNov2019, date25thNov2019);
        
        DeliveryServiceFactory.setupMockDeliveryService();
        DeliveryService service = DeliveryServiceFactory.getDeliveryService();
        
        BikeType testTypeMTB1 = new BikeType(bd5, bd50, "Mountain Bike");
        BikeType testTypeMTB2 = new BikeType(bd7, bd70, "Mountain Bike");
        BikeType testTypeRDB1 = new BikeType(bd7, bd70, "Road Bike");
        Location prov1Loc = new Location("B84 7ER", "Address");
        provider1 = new Provider("provider1", "email", prov1Loc, new BigDecimal(5),
                "placeholder opening times", "placeholder phone number");
        MTB11 = new Bike(testTypeMTB1);
        provider1.addBike(MTB11);
        MTB12 = new Bike(testTypeMTB1);
        provider1.addBike(MTB12);
        MTB13 = new Bike(testTypeMTB1);
        provider1.addBike(MTB13);
        MTB21 = new Bike(testTypeMTB2);
        provider1.addBike(MTB21);
        MTB22 = new Bike(testTypeMTB2);
        provider1.addBike(MTB22);
        Location prov2Loc = new Location("B84 7ER", "Address");
        provider2 = new Provider("provider1", "email", prov2Loc, new BigDecimal(5),
                "placeholder opening times", "placeholder phone number");
        MTB14 = new Bike(testTypeMTB1);
        provider2.addBike(MTB14);
        MTB15 = new Bike(testTypeMTB1);
        provider2.addBike(MTB15);
        RDB11 = new Bike(testTypeRDB1);
        provider2.addBike(RDB11);
        RDB12 = new Bike(testTypeRDB1);
        provider2.addBike(RDB12);
        RDB13 = new Bike(testTypeRDB1);
        provider2.addBike(RDB13);
        providers = new ArrayList<Provider>();
        providers.add(provider1);
        providers.add(provider2);
        
        theController = new Controller(providers, service);
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
        // Dr Bees is intended to live within range of the providers
        drBeesAddress = new Location("B84 7BeeR", "Bee street");
        Customer drBees = new Customer("Doctor", "Bees", "waspThemedSuperHero@beemail.com"
                , "01436678850" , drBeesAddress);
        customers.add(drBees);
        // Star Man lives too far away to get his bikes delivered
        Location tooFarAway = new Location("inSpace", "youHeardThatRight");
        Customer starMan = new Customer("Star", "Man", "waitingInTheSky@gmail.com"
                , "no reception in space", tooFarAway);
        customers.add(starMan);
        
        
        
    }
    
    // TODO: Write system tests covering the three main use cases
    
    @Test
    void testNullGetAvailable1() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Bike> shouldBeNull = provider1.getAvailableBikes(10, searchTypes, testDateRange);
        assertEquals(shouldBeNull, null);
    }
    
    @Test
    void testNullGetAvailable2() {
        provider1.setBikesUnavailable(provider1.getBikeList(), testDateRange);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Bike> shouldBeNull = provider1.getAvailableBikes(1, searchTypes, testDateRange);
        assertEquals(shouldBeNull, null);
        
    }

    @Test 
    void testRegularGet() { // I.e there are providers within range of the customer
                            // (in this instance, drBees), with bikes available for 
                            // hire.
        BigDecimal expectedPrice = new BigDecimal(60);
        BigDecimal expectedDeposit = new BigDecimal(42);
        ArrayList<Bike> p1Bikes = new ArrayList<>();
        p1Bikes.add(MTB11);
        p1Bikes.add(MTB12);
        Quote expectedQuote1 = new Quote(p1Bikes, provider1, testDateRange
                , expectedPrice, expectedDeposit);
        ArrayList<Bike> p2Bikes = new ArrayList<>();
        p2Bikes.add(MTB14);
        p2Bikes.add(MTB15);
        Quote expectedQuote2 = new Quote(p2Bikes, provider2, testDateRange
                , expectedPrice, expectedDeposit);
        ArrayList<Quote> expectedQuotes = new ArrayList<>();
        expectedQuotes.add(expectedQuote1);
        expectedQuotes.add(expectedQuote2);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Quote> actualQuotes = theController.getQuotes(drBeesAddress
                , testDateRange, searchTypes, 2);
        Collections.sort(actualQuotes);
        Collections.sort(expectedQuotes);
        boolean testResult = compareQuoteList(actualQuotes, expectedQuotes);
        assertTrue(testResult);
    }
    
    @Test
    void testRegularGetAvailableBikes() {
        ArrayList<Bike> expectedBikes = new ArrayList<Bike>();
        expectedBikes.add(MTB11);
        expectedBikes.add(MTB12);
        expectedBikes.add(MTB13);
        expectedBikes.add(MTB21);
        expectedBikes.add(MTB22);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");

        ArrayList<Bike> actualBikes = provider1.getAvailableBikes(5, searchTypes, testDateRange);
        
        Collections.sort(expectedBikes);
        Collections.sort(actualBikes);
        
        boolean testResult = compareBikeList(expectedBikes, actualBikes);
        
        assertTrue(testResult);
    }
}
