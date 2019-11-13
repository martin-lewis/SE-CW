package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    BikeType(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
    
    private BigDecimal dailyPrice;
    
    public BigDecimal getDailyPrice() {
        return this.dailyPrice;
    }
    
    public void setDailyPrice(BigDecimal newPrice) { //Maybe this should not be public if only the interfaces can change it
        this.dailyPrice = newPrice;
    }
    
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        assert false;
        return null;
    }
}