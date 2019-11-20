package uk.ac.ed.bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Provider> providers;
    private DeliveryService deliveryService;
    
    /**
     * Registers the return of a booking to its provider
     * @return A BigDecimal representing the deposit owed in pounds
     */
    public BigDecimal registerReturn(Booking booking) {
        return booking.providerReturn();
    }
    
    /**
     * Registers the return of a booking to a partner of its provider.
     * Note that input validation need not be done on the partner,
     *  as this would occur when the partner uses the register
     *  return feature on the website.
     * @return A BigDecimal representing the deposit owed in pounds
     */
    public BigDecimal registerReturnPartner(Booking booking, Provider partner) {
        Location partAddress = partner.getAddress();
        Location provAddress = booking.getProviderAddress();
        LocalDate pickupDate = booking.getEndDate();
        deliveryService.scheduleDelivery(booking, partAddress, provAddress, pickupDate);
        String email = "placeholder";
        String provEmail = booking.getProviderEmail();
        sendEmail(email, provEmail);
        return booking.partnerReturn();
    }
    
    /**
     * Sends an email
     * @param email
     * @param address the email address
     */
    private void sendEmail(String email, String address) {
        return;
    }

}
