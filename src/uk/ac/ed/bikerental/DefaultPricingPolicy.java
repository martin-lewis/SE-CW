package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class DefaultPricingPolicy implements PricingPolicy {

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        bikeType.setDailyPrice(dailyPrice); //Sets the price of that BikeType

    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        assert(duration.toDays() >= 0); //Duration start should be before end
        assert(bikes != null); //Checks that there are actually bikes
                
        BigDecimal price = new BigDecimal(0); //Sets a BigDecimal to add the price of each bike to
        
        for (Bike aBike: bikes) { //Iterator that runs over the collection of bikes
            price = price.add(aBike.getType().getDailyPrice()); //Adds to price the price of the next bike in the collection
        }
        
        BigDecimal length = new BigDecimal(duration.toDays() + (long)1); //We need to add 1 as toDays does not count the first day of a duration
        price = price.multiply(length); //Multiplies by the length of the bike hire
                
        return price;
    }

}
