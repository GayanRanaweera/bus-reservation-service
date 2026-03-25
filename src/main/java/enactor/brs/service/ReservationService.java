package enactor.brs.service;

import enactor.brs.constant.Constants;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.ReservationRequest;
import enactor.brs.model.response.ReservationResponse;
import enactor.brs.store.InMemoryStore;
import enactor.brs.util.PriceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ReservationService {

    private static final Logger logger = LogManager.getLogger(ReservationService.class);
    private final InMemoryStore store = InMemoryStore.getInstance();

    /**
     * Reserve seats for given request.
     * This method is synchronized to avoid race conditions.
     */
    public synchronized ReservationResponse reserveSeats(ReservationRequest reservationRequest) {

        int passengers = reservationRequest.getPassengers();
        String origin = reservationRequest.getOrigin();
        String destination = reservationRequest.getDestination();
        double confirmPrice = reservationRequest.getConfirmPrice();

        ReservationResponse reservationResponse = new ReservationResponse();

        // Calculate actual price
        double pricePerSeat = PriceUtil.getPricePerSeat(origin, destination);
        double totalPrice = pricePerSeat * passengers;

        // Validate price
        if (Math.abs(confirmPrice - totalPrice) > 0) {
            logger.error("Error in reserveSeats:{}", ErrorMessage.PRICE_MISMATCH.getMessage());
            reservationResponse.setStatus(Constants.STATUS_FAIL);
            reservationResponse.setReason(ErrorMessage.PRICE_MISMATCH.getMessage());
            return reservationResponse;
        }

        // Get available seats
        List<String> available = store.getAvailableSeats();

        if (available.size() < passengers) {
            logger.error("Error in reserveSeats:{}", ErrorMessage.NOT_ENOUGH_SEATS.getMessage());
            reservationResponse.setStatus(Constants.STATUS_FAIL);
            reservationResponse.setReason(ErrorMessage.NOT_ENOUGH_SEATS.getMessage());
            return reservationResponse;
        }

        // Assign seats
        List<String> assigned = new ArrayList<>(available.subList(0, passengers));

        // Generate ticket number
        String ticketNumber = generateTicketNumber();

        // Update store (CRITICAL SECTION)
        store.getBookedSeats().addAll(assigned);
        store.getTicketSeatMap().put(ticketNumber, assigned);

        reservationResponse.setStatus(Constants.STATUS_SUCCESS);
        reservationResponse.setOrigin(origin);
        reservationResponse.setDestination(destination);
        reservationResponse.setTotalPrice(totalPrice);
        reservationResponse.setTicketNumber(ticketNumber);
        reservationResponse.setSeats(assigned);

        return reservationResponse;
    }

    /**
     * Generate unique ticket number
     */
    public String generateTicketNumber() {
        return "T-" + UUID.randomUUID().toString().substring(0, 8);
    }

}