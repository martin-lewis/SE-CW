package uk.ac.ed.bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Provider> providers;
    private DeliveryService deliveryService;
    
    public Controller(ArrayList<Provider> providers, DeliveryService del) {
        this.providers = providers;
        this.deliveryService = del;
    }
    
    public ArrayList<Provider> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

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
    /**
     * 
     * @param address
     * @param dates
     * @param types
     * @param noBikes
     * @return
     */
    public ArrayList<Quote> getQuotes(Location address, DateRange dates, ArrayList<String> types, int noBikes){
        ArrayList<Quote> quotes = new ArrayList<Quote>(); //ArrayList to hold found quotes
        ArrayList<Provider> inRangeProviders = new ArrayList<Provider>(); //ArrayList to find all providers that are close
        
        for (Provider provider : providers) { //For all providers in the system
            Location provAddress = provider.getAddress(); //Gets providers address
            if (provAddress.isNearTo(address)) { //If the customer address and providers address is close then
                inRangeProviders.add(provider); //It is added to the close providers
            }
        }
        
        for (Provider provider : inRangeProviders) { //For each found provider in range
            ArrayList<Bike> availableBikes = new ArrayList<Bike>(); //List to hold return
            availableBikes = provider.getAvailableBikes(noBikes, types, dates); //Gets the bikes the provider has available
            if (availableBikes != null) { //If the bikes is null there are no bikes so if its not null and there are
                BigDecimal hirePrice = provider.calculateHirePrice(availableBikes, dates); //Calculates hirePrice
                BigDecimal deposit = provider.calculateDeposit(availableBikes);
                Quote newQuote = new Quote(availableBikes, provider, dates, hirePrice, deposit);
                quotes.add(newQuote);
            }
        }
        
        if (quotes.size() > 0) {
            return quotes;
        } else {
            for (int i = -3; i < 4; i++) {
                if (i==0) {
                    continue;
                }
                DateRange newDate = dates.shift(i);
                
                for (Provider provider : inRangeProviders) {
                    ArrayList<Bike> availableBikes = new ArrayList<Bike>();
                    availableBikes = provider.getAvailableBikes(noBikes, types, newDate);
                    if (availableBikes != null) {
                        BigDecimal hirePrice = provider.calculateHirePrice(availableBikes, newDate);
                        BigDecimal deposit = provider.calculateDeposit(availableBikes);
                        Quote newQuote = new Quote(availableBikes, provider, newDate, hirePrice, deposit);
                        quotes.add(newQuote);
                    }
                }
            }
            if (quotes.size() > 0) {
                return quotes;
            } else {
                return null;
            }
        }
        
    }

}
