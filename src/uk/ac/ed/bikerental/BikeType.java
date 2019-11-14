package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    
    private BigDecimal dailyPrice;
    private BigDecimal replacementValue;
    
    /**
     * Constructor for BikeType taking a dailyPrice
     * @param dailyPrice
     */
    BikeType(BigDecimal dailyPrice, BigDecimal replacementValue) {
        this.dailyPrice = dailyPrice;
        this.replacementValue = replacementValue;
    }  
    
    
    public BigDecimal getDailyPrice() {
        return this.dailyPrice;
    }
    
    public void setDailyPrice(BigDecimal newPrice) { //Maybe this should not be public if only the interfaces can change it
        this.dailyPrice = newPrice;
    }
    
    public void setReplacementValue(BigDecimal newPrice) {
        this.replacementValue = newPrice;
    }
    
    public BigDecimal getReplacementValue() {
        return this.replacementValue;
    }
}