package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MockValuationPolicy implements ValuationPolicy {

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        if(bike.getAge() == 5) {
            return bike.getReplacementCost().multiply(new BigDecimal(0.5));
        }
        return bike.getReplacementCost();
    }

}
