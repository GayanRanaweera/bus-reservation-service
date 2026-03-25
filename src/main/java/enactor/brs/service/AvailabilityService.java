package enactor.brs.service;

import enactor.brs.constant.Constants;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.response.AvailabilityResponse;
import enactor.brs.store.InMemoryStore;
import enactor.brs.util.PriceUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AvailabilityService {

    private static final Logger logger = LogManager.getLogger(AvailabilityService.class);

    /**
     * Method to check availability and price.
     */
    public AvailabilityResponse checkAvailability(AvailabilityRequest availabilityRequest) {

        AvailabilityResponse availabilityResponse = new AvailabilityResponse();

        int availableSeats = getAvailableSeats();

        // If not enough seats → fail response
        if (availableSeats < availabilityRequest.getPassengers()) {
            availabilityResponse.setAvailableSeats(availableSeats);
            logger.error("Error in checkAvailability:{}", ErrorMessage.NOT_ENOUGH_SEATS.getMessage());
            availabilityResponse.setStatus(Constants.STATUS_FAIL);
            availabilityResponse.setReason(ErrorMessage.NOT_ENOUGH_SEATS.getMessage());
            return availabilityResponse;
        }

        // Success case
        availabilityResponse.setAvailableSeats(getAvailableSeats());
        availabilityResponse.setTotalPrice(calculatePrice(availabilityRequest));
        return availabilityResponse;
    }

    /**
     * Get currently available seats from store
     */
    public int getAvailableSeats() {
        InMemoryStore store = InMemoryStore.getInstance();
        return store.getAllSeats().size() - store.getBookedSeats().size();
    }

    /**
     * Calculate total price based on passengers
     */
    public double calculatePrice(AvailabilityRequest availabilityRequest) {
        int passengers = availabilityRequest.getPassengers();
        double price = getPricePerSeat(availabilityRequest);
        return price * passengers;
    }

    /**
     * Get price per seat based on route
     */
    private double getPricePerSeat(AvailabilityRequest availabilityRequest) {
        String origin = availabilityRequest.getOrigin();
        String destination = availabilityRequest.getDestination();
        // Normalize input for consistency
        return PriceUtil.getPricePerSeat(origin.trim().toUpperCase(), destination.trim().toUpperCase());
    }
}