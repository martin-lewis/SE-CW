package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        DeliveryService service = DeliveryServiceFactory.getDeliveryService();
        
        BikeType testTypeMTB1 = new BikeType(bd5, bd50, "Mountain Bike");
        BikeType testTypeMTB2 = new BikeType(bd7, bd70, "Mountain Bike");
        BikeType testTypeRDB1 = new BikeType(bd7, bd70, "Road Bike");
        Location prov1Loc = new Location("B84 7ER", "Address");
        Provider provider1 = new Provider("provider1", "email", prov1Loc, new BigDecimal(5),
                "placeholder opening times", "placeholder phone number");
        for(int i = 0; i < 4; i++) {
            provider1.addBike(new Bike(testTypeMTB1));
        }
        for(int i = 0; i < 2; i++) {
            provider1.addBike(new Bike(testTypeMTB2));
        }
        Location prov2Loc = new Location("B84 7ER", "Address");
        Provider provider2 = new Provider("provider1", "email", prov2Loc, new BigDecimal(5),
                "placeholder opening times", "placeholder phone number");
        for(int i = 0; i < 4; i++) {
            provider1.addBike(new Bike(testTypeRDB1));
        }
        for(int i = 0; i < 2; i++) {
            provider1.addBike(new Bike(testTypeMTB1));
        }
        ArrayList<Provider> providers = new ArrayList<Provider>();
        providers.add(provider1);
        providers.add(provider2);
        
        Controller theController = new Controller(providers, service);
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
        // Dr Bees is intended to live within range of the providers
        Location drBeesAddress = new Location("B84 7BeeR", "Bee street");
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
    void testGetQuotes() {
        
    }
}
