package uk.ac.ed.bikerental;

public class Location {
    /**
     * The postcode of the location
     */
    private String postcode;
    /**
     * The address of the location
     */
    private String address;
    
    /**
     * Constructor for a location
     * @param postcode The postcode of the location
     * @param address The address of the location
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    /**
     * Checks if this location is 'near to' another location, by checking the first
     * two characters of the post code
     * @param other the other location we check on
     * @return true if the two locations are near each other
     */
    public boolean isNearTo(Location other) {
        char thisFirstDigit = this.postcode.charAt(0);
        char thisSecondDigit = this.postcode.charAt(1);
        char otherFirstDigit = other.postcode.charAt(0);
        char otherSecondDigit = other.postcode.charAt(1);
        return((thisFirstDigit == otherFirstDigit) && (thisSecondDigit == otherSecondDigit));
    }
    /**
     * Gets the postcode
     * @return the postcode, as a String
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * Gets the address
     * @return the address, as a String
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
