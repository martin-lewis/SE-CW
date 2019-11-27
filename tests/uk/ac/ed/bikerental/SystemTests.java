package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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
    DateRange testDateRange1;
    
    Customer drBees;
    Location drBeesAddress;
    
    Customer starMan;
    Location tooFarAway;
    
    Quote testQuote1;
    
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
        testDateRange1 = new DateRange(date20thNov2019, date25thNov2019);
        
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
        drBees = new Customer("Doctor", "Bees", "waspThemedSuperHero@beemail.com"
                , "01436678850" , drBeesAddress);
        customers.add(drBees);
        // Star Man lives too far away to get his bikes delivered
        tooFarAway = new Location("inSpace", "youHeardThatRight");
        starMan = new Customer("Star", "Man", "waitingInTheSky@gmail.com"
                , "no reception in space", tooFarAway);
        customers.add(starMan);
        
        ArrayList<Bike> q1Bikes= new ArrayList<>();
        q1Bikes.add(MTB11);
        q1Bikes.add(MTB12);
        testQuote1 = new Quote(q1Bikes, provider1, testDateRange1, bd50, bd50);
        
    }
    
    @Test
    public void bookQuoteAssertion1() {
        assertThrows(AssertionError.class, () -> theController.bookQuote(null, drBees, null));
    }
    
    @Test
    public void bookQuoteAssertion2() {
        assertThrows(AssertionError.class, () -> theController.bookQuote(testQuote1, null, null));
    }
    /* TODO: Write system tests covering the three main use cases
     * For bookQuotes
     * - normal one: test that the booking's attributes are good
     * - normal one: test that bikes are set unavailable
     * - normal ones: check booking is added to customer
     * -              and provider
     * - normal ones: where address is provided
     * - assertionError ones
     */
    
    //bookQuotes tests
    @Test
    void testRegularBook1() {
        Booking testBooking = theController.bookQuote(testQuote1, drBees, null);
        ArrayList<Bike> q1Bikes= new ArrayList<>();
        q1Bikes.add(MTB11);
        q1Bikes.add(MTB12);
        assertEquals(testBooking.getBikes(), q1Bikes);
        assertEquals(testBooking.getCustomer(), drBees);
        assertEquals(testBooking.getProvider(), provider1);
        assertEquals(testBooking.getDeposit(), bd50);
        assertEquals(testBooking.getCost(), bd50);
        assertEquals(testBooking.getDuration(), testDateRange1);
        assertEquals(testBooking.getState(), "Booked");
    }
    
    @Test
    void testRegularBook2() {   // Tests that bike are correctly set unavailable
        Booking testBooking = theController.bookQuote(testQuote1, drBees, null);
        for(Bike bike : testBooking.getBikes()) {
            assertTrue(bike.getUnavailabilities().contains(testDateRange1));
        }
    }
    
    //getQuotes tests
    @Test
    void testNullGetAvailable1() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Bike> shouldBeNull = provider1.getAvailableBikes(10, searchTypes, testDateRange1);
        assertEquals(shouldBeNull, null);
    }
    
    @Test
    void testNullGetAvailable2() {
        provider1.setBikesUnavailable(provider1.getBikeList(), testDateRange1);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Bike> shouldBeNull = provider1.getAvailableBikes(1, searchTypes, testDateRange1);
        assertEquals(shouldBeNull, null);
        
    }
    
    @Test
    void testRegularGetAvailableBikes() {   // Tests that getAvailableBikes() works as expected when
                                            // bikes are available
        ArrayList<Bike> expectedBikes = new ArrayList<Bike>();
        expectedBikes.add(MTB11);
        expectedBikes.add(MTB12);
        expectedBikes.add(MTB13);
        expectedBikes.add(MTB21);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");

        ArrayList<Bike> actualBikes = provider1.getAvailableBikes(4, searchTypes, testDateRange1);
        
        Collections.sort(expectedBikes);
        Collections.sort(actualBikes);
        
        boolean testResult = compareBikeList(expectedBikes, actualBikes);
        
        assertTrue(testResult);
    }
    
    @Test 
    void testCalculateDepositAssert1() {    // Checks that calculateDeposit has an assertionError when 
                                            // passed an empty list of bikes
        ArrayList<Bike> empty = new ArrayList<Bike>();
        assertThrows(AssertionError.class, () -> provider1.calculateDeposit(empty, testDateRange1.getStart()));
    }
    
    @Test
    void testCalculateDepositAssert2() {     //Checks that calculateDeposit fails when passed bikes not
                                            // belonging to the provider it is called on
        ArrayList<Bike> notProv1s = new ArrayList<>();
        notProv1s.add(MTB14);
        assertThrows(AssertionError.class, () -> provider1.calculateDeposit(notProv1s, testDateRange1.getStart()));
        
    }
    
    @Test 
    void testCalculateHirePriceAssert1() {    // Checks that calculateHirePrice has an assertionError when 
                                              // passed an empty list of bikes
        ArrayList<Bike> empty = new ArrayList<Bike>();
        assertThrows(AssertionError.class, () -> provider1.calculateHirePrice(empty, testDateRange1));
    }
    
    @Test
    void testCalculateHirePriceAssertNotBikes() { //Checks calculateHirePrice has an assertionError when it given
        ArrayList<Bike> notTheirBikes = new ArrayList<Bike>(); //Bikes that don't belong to it
        notTheirBikes.add(this.RDB11); //This bike belongs to provider2
        assertThrows(AssertionError.class, () -> provider1.calculateHirePrice(notTheirBikes, testDateRange1));
    }

    @Test 
    void testRegularGetQuotes() { // I.e there are providers within range of the customer
                            // (in this instance, drBees), with bikes available for 
                            // hire.
        BigDecimal price1 = new BigDecimal(60);
        BigDecimal price2 = new BigDecimal(42);
        ArrayList<Bike> p1Bikes = new ArrayList<>();
        p1Bikes.add(MTB11);
        p1Bikes.add(MTB12);
        Quote testQuote1 = new Quote(p1Bikes, provider1, testDateRange1
                , price1, price2);
        ArrayList<Bike> p2Bikes = new ArrayList<>();
        p2Bikes.add(MTB14);
        p2Bikes.add(MTB15);
        Quote testQuote2 = new Quote(p2Bikes, provider2, testDateRange1
                , price1, price2);
        ArrayList<Quote> expectedQuotes = new ArrayList<>();
        expectedQuotes.add(testQuote1);
        expectedQuotes.add(testQuote2);
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Quote> actualQuotes = theController.getQuotes(drBeesAddress
                , testDateRange1, searchTypes, 2);
        Collections.sort(actualQuotes);
        Collections.sort(expectedQuotes);
        boolean testResult = compareQuoteList(actualQuotes, expectedQuotes);
        assertTrue(testResult);
    }
    
    @Test
    void testGetQuotesOutOfRange() {    // Checks that GetQuotes does not return when the customer 
                                        // is out of range of providers
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Quote> actualQuotes = theController.getQuotes(tooFarAway
                , testDateRange1, searchTypes, 2);
        assertTrue(actualQuotes == null);
    }
    
    @Test
    void testGetQuotesNotEnoughBikes() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        ArrayList<Quote> actualQuotes = theController.getQuotes(drBeesAddress
                , testDateRange1, searchTypes, 20);
        assertTrue(null == actualQuotes);    
    }
    
    @Test
    void testGetQuotesAssertion1() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        assertThrows(AssertionError.class, () -> theController.getQuotes(drBeesAddress
                , testDateRange1, searchTypes, 0));
    }
    
    @Test
    void testGetQuotesAssertion2() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        assertThrows(AssertionError.class, () -> theController.getQuotes(drBeesAddress
                , testDateRange1, null, 0));
    }
    
    @Test
    void testGetQuotesAssertion3() {
        ArrayList<String> searchTypes = new ArrayList<>();
        assertThrows(AssertionError.class, () -> theController.getQuotes(drBeesAddress
                , testDateRange1, searchTypes, 0));
    }
    
    @Test
    void testGetQuotesAssertion4() {
        ArrayList<String> searchTypes = new ArrayList<>();
        searchTypes.add("Mountain Bike");
        assertThrows(AssertionError.class, () -> theController.getQuotes(null
                , testDateRange1, searchTypes, 0));
    }
    
}
