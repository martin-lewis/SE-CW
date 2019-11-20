package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    
    /**
     * The broad type that this bike falls under, I.E "mountain bike"
     *  or "road bike"
     */
    private final String genericType;
    private BigDecimal dailyPrice;
    private BigDecimal replacementValue;
    
    /**
     * Constructor for BikeType taking a dailyPrice
     * @param dailyPrice The daily price to hire the bike type
     * @param replacementValue The replacement value of the bike type
     */
    BikeType(BigDecimal dailyPrice, BigDecimal replacementValue
            , String genericType) {
        this.dailyPrice = dailyPrice;
        this.replacementValue = replacementValue;
        this.genericType = genericType;
    } 
    
    public String getGenericType() {
        return genericType;
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