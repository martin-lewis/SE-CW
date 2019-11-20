package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * This class is used to hold a date range made up of a start
 * and end date, it also includes several methods handle and work
 * with these objects
 */

public class DateRange {
    /**
     * The dates for the start and end dates
     */
    private LocalDate start, end;
    
    
    /**
     * Constructor that takes two dates to make the range
     * @param start LocalDate for the starting date of the range
     * @param end LocalDate for the ending date of the range
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Getter for start
     * @return Starting LocalDate of the range
     */
    public LocalDate getStart() {
        return this.start;
    }
    /**
     * Getter for end
     * @return Ending LocalDate of the range
     */
    public LocalDate getEnd() {
        return this.end;
    }
    /**
     * Calculates the number of years between the two dates
     * @return Long containing the number of years
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }
    /**
     * Calculates the number of days between the two dates
     * @return Long of the number of days
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }
    /**
     * This method takes another DateRange and checks if this date range and the given date range overlap
     * @param other Another DateRange object
     * @return Boolean value true or false if it overlaps or not
     */
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
        if(this.end.isAfter(other.start) && this.end.isBefore(other.end)) {
            return true;
        }
        else if(other.end.isAfter(this.start) && other.end.isBefore(this.end)) {
            return true;
        }
        return false;
    }
    /**
     * Method returns the hash code of the DateRange object
     * @return Hash to represent the objects
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }
    /**
     * Overridden equals function which checks if the start and end dates in both are the same
     * @param obj An object of any type, should be a DateRange object or it will return false
     * @return Boolean representing if they are the same object or not
     */
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
    
    public DateRange shift(int noDays) {
        LocalDate newStart = this.start.plus(noDays, ChronoUnit.DAYS);
        LocalDate newEnd = this.start.plus(noDays, ChronoUnit.DAYS);
        return new DateRange(newStart, newEnd);
    }
}
