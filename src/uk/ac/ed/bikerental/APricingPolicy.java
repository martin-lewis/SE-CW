package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

public class APricingPolicy implements PricingPolicy {
    
    private HashMap<Long,Integer> policy; //Maps number of days to discount
    
    /**
     * Constructor for APricingPolicy. Initialises policy wth a 
     *  single entry for a 0% discount on day 0
     */
    public APricingPolicy () {
        policy = new HashMap<Long, Integer>();
        policy.put((long) 0, 0);
    }
    
    /**
     * Adds a discount entry to the policy.
     * @param length Minimum number of days for the discount to apply 
     *  - discount applies until another one would be applicable instead.
     * @param discount  The discount as a percentage -  i.e 50% 
     *  discount would require you to put in 50. This must be below 101
     *  and above -1
     */
    public void setDiscount(Long length, int discount) {
        if (discount > 100 || discount < 0) {
            throw new IllegalArgumentException("Discount cannot be more than 100");
            //We don't need to worry about dealing with the exception as it will not be called by our system
        }
        policy.put(length, discount);
    }
    
    /**
     * removes a discount from policy
     * @param length the length of the discount you want to remove
     */
    public void removeDiscount(Long length) { //removes a discount
        if ((length != 0) && (policy.containsKey(length))) { //If the length is not the default 0 and is in the mapping
            policy.remove(length); //Then it is removed
        }
        //Else nothing happens, the thing they want removed is not there
    }
    
    //@ required duration>=0
    //@ ensures \result>=0
    private int getDiscount(Long duration) {
        for (Long i = duration; i >=0; i--) {
            if (policy.containsKey(i)) {
                return policy.get(i);
            }
        }
        return 0;
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        bikeType.setDailyPrice(dailyPrice);
        
    }
    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        assert(duration.toDays() >= 0); //Duration start should be before end
        assert(bikes != null);
        
        int discount = getDiscount(duration.toDays()); //This gets the discount for the length of the booking
        
        BigDecimal price = new BigDecimal(0); //Sets a price value to add the price of each bike to
        
        for (Bike aBike: bikes) { //Iterator that runs over the collection of bikes
            price = price.add(aBike.getType().getDailyPrice()); //Adds to price the price of the next bike in the collection
        }
        
        BigDecimal length = new BigDecimal(duration.toDays() + (long)1); //We need to add 1 as toDays does not count the first day of a duration
        price = price.multiply(length); //Multiplies by the length of the bike hire
        
        BigDecimal oneHundred = new BigDecimal(100); //Makes a big decimal of 100
        BigDecimal discountDecimal = new BigDecimal(100 - discount); //Uses discount to get final price as a percentage of original in BigDecimal form
        discountDecimal = discountDecimal.divide(oneHundred); //Turns discount decimal into a multiplier for a price
        
        price = price.multiply(discountDecimal); //Multiplies the full calculated price by the percentage
        
        return price;
    }
    
    

}
