package uk.ac.ed.bikerental;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
     * This method takes a set of conditions and attempts to find suitable quotes within the providers listed
     * in the controller. If no quotes can be found within the dates given then it will check 3 days either side of
     * the initial dates. If still no quotes can be found then it will return null
     * @param address The address the customer has given
     * @param dates The dates over which the customer wants to hire
     * @param types The types of the bikes they want
     * @param noBikes The number of bikes they want
     * @return Will return either a list of one or more quotes or null
     * @see Quote
     * @see Provider
     */
    public ArrayList<Quote> getQuotes(Location address, DateRange dates, ArrayList<String> types, int noBikes){
        assert(noBikes > 0); //Checks there are more than 0 bikes wanted
        assert(types != null); //Checks the bikes is not null
        assert(types.size() >0); //Checks there are more than 0 types
        assert(address != null); //Checks there is an address
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
                BigDecimal deposit = provider.calculateDeposit(availableBikes, dates.getStart()); //Calculates deposit
                Quote newQuote = new Quote(availableBikes, provider, dates, hirePrice, deposit); //Creates this new quote
                quotes.add(newQuote); //Adds the new quote
            }
        }
        
        if (quotes.size() > 0) { //If there are some quotes
            return quotes; //they are returned
        } /*else { //otherwise if there are none we look around other dates
            for (int i = -3; i < 4; i++) { //Runs through -3 to 3 inclusive representing the number of days we look either side of the original
                if (i==0) {  //Skip a shift of 0 days as we have already looked at it
                    continue; //Moves to next i value
                }
                DateRange newDate = dates.shift(i); //Generates new date
                //Runs the code from before on the new dates
                for (Provider provider : inRangeProviders) {
                    ArrayList<Bike> availableBikes = new ArrayList<Bike>();
                    availableBikes = provider.getAvailableBikes(noBikes, types, newDate);
                    if (availableBikes != null) {
                        BigDecimal hirePrice = provider.calculateHirePrice(availableBikes, newDate);
                        BigDecimal deposit = provider.calculateDeposit(availableBikes, dates.getStart());
                        Quote newQuote = new Quote(availableBikes, provider, newDate, hirePrice, deposit);
                        quotes.add(newQuote);
                    }
                }
            }
            if (quotes.size() > 0) { //If once we have run the shifted dates we have some quotes
                return quotes; //they are returned
            } else { //otherwise we have exhausted all options
                return null; //null is returned
            }
        }
        */
        return null;
    }
    /**
     * Take a quote and confirms it as a booking updating the internal state and scheduling delivery
     * if required
     * @param quote The Quote that is being booked
     * @param customer A Customer object containing the customers details
     * @param address The address for bike delivery
     * @return The booking generates by this method 
     */
    public Booking bookQuote(Quote quote, Customer customer, Location address) {
        Booking booking = new Booking(quote, customer, address); //Creates a booking object
        sendEmail(customer.getEmail(), "Placeholder"); //Sends an email TODO add the body of the email
        Provider provider = quote.getProvider(); //Gathers needed details from the quote
        ArrayList<Bike> bikes = quote.getBikes();
        DateRange dates = quote.getDuration();
        LocalDate startDate = dates.getStart();
        
        
        provider.addBooking(booking); //Adds the booking to provider
        provider.setBikesUnavailable(bikes, dates); //Sets the bikes unavailable
        customer.addBooking(booking); //Adds the booking to the customer
        Location providerAddress = provider.getAddress(); //Gets the providers address
        
        //SCHEDULE DELIVERY
        if (address != null) { //If an address has been provided, i.e. they want delivery
            this.deliveryService.scheduleDelivery(booking, providerAddress, address, startDate); //Delivery is scheduled
        }
        return booking;
        
    }

}
