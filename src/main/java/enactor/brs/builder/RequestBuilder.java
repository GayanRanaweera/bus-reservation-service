package enactor.brs.builder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.request.ReservationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestBuilder {

    private static final Logger logger = LogManager.getLogger(RequestBuilder.class);

    /**
     * Build request from GET parameters
     */
    public static AvailabilityRequest buildAvailabilityRequest(HttpServletRequest request)
            throws IllegalArgumentException {

        AvailabilityRequest availabilityRequest = new AvailabilityRequest();

        // Parse input parameters
        String passengersStr = request.getParameter("passengers");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");

        // Validate input parameters
        int passengers;
        if (passengersStr == null || passengersStr.trim().isEmpty()) {
            logger.error("Error in buildAvailabilityRequest: {}",ErrorMessage.PASSENGERS_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.PASSENGERS_REQUIRED.getMessage());
        }
        try {
            passengers = Integer.parseInt(passengersStr.trim());
        }catch (NumberFormatException e){
            logger.error("Error in buildAvailabilityRequest: {}", e);
            throw new IllegalArgumentException(ErrorMessage.PASSENGERS_MUST_BE_NUMBER.getMessage());
        }

        if (origin == null || origin.trim().isEmpty()) {
            logger.error("Error in buildAvailabilityRequest: {}",ErrorMessage.ORIGIN_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ORIGIN_REQUIRED.getMessage());
        }

        if (destination == null || destination.trim().isEmpty()) {
            logger.error("Error in buildAvailabilityRequest: {}",ErrorMessage.DESTINATION_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.DESTINATION_REQUIRED.getMessage());
        }

        // Normalize input parameters
        availabilityRequest.setPassengers(passengers);
        availabilityRequest.setOrigin(origin.trim().toUpperCase());
        availabilityRequest.setDestination(destination.trim().toUpperCase());

        return availabilityRequest;
    }

    /**
     * Build request from JSON body (POST)
     */
    public static ReservationRequest buildReservationRequest(String payLoad)
            throws IllegalArgumentException, IOException {

        Gson gson = new Gson();
        ReservationRequest reservationRequest;

        // Parse request object and validate
        try {
            reservationRequest = gson.fromJson(payLoad, ReservationRequest.class);
        } catch (JsonSyntaxException e) {
            logger.error("Error in buildReservationRequest: {}",e);
            throw new IllegalArgumentException(ErrorMessage.INVALID_JSON_FORMAT_OR_DATA_TYPE.getMessage());
        } catch (Exception e) {
            logger.error("Error in buildReservationRequest: {}",e);
            throw new IllegalArgumentException(ErrorMessage.INVALID_REQUEST_BODY.getMessage());
        }

        // Object and fields validations
        if (reservationRequest == null) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.EMPTY_REQUEST_BODY.getMessage());
            throw new IllegalArgumentException(ErrorMessage.EMPTY_REQUEST_BODY.getMessage());
        }

        Integer passengers = reservationRequest.getPassengers();
        if (passengers == null) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.PASSENGERS_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.PASSENGERS_REQUIRED.getMessage());
        }
        if (passengers <= 0) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.INVALID_PASSENGERS.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSENGERS.getMessage());
        }

        String origin = reservationRequest.getOrigin();
        if (origin == null || origin.trim().isEmpty()) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.ORIGIN_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ORIGIN_REQUIRED.getMessage());
        }

        String destination = reservationRequest.getDestination();
        if (destination == null || destination.trim().isEmpty()) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.DESTINATION_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.DESTINATION_REQUIRED.getMessage());
        }

        Double confirmPrice = reservationRequest.getConfirmPrice();
        if (confirmPrice == null) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.CONFIRM_PRICE_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.CONFIRM_PRICE_REQUIRED.getMessage());
        }
        if (confirmPrice <= 0) {
            logger.error("Error in buildReservationRequest: {}",ErrorMessage.INVALID_CONFIRM_PRICE.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONFIRM_PRICE.getMessage());
        }

        // normalize
        reservationRequest.setOrigin(origin.trim().toUpperCase());
        reservationRequest.setDestination(destination.trim().toUpperCase());

        return reservationRequest;
    }

}