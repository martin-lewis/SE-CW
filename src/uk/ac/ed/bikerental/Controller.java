package uk.ac.ed.bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Provider> providers;
    private DeliveryService deliveryService;
    
    public BigDecimal registerReturn(Booking booking) {
        return booking.providerReturn();
    }
    
    public BigDecimal registerReturnPartner(Booking booking, Provider partner) {
        Location partAddress = partner.getAddress();
        Location provAddress = booking.getProviderAddress();
        LocalDate pickupDate = booking.getEndDate();
        deliveryService.scheduleDelivery(booking, partAddress, provAddress, pickupDate);
        String email = "placeholder";
        String provEmail = booking.getProviderEmail();
        sendEmail(email, provEmail);
    }
    
    private void sendEmail(String email, String address) {
        return;
    }

}
