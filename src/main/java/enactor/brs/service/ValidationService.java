package enactor.brs.service;

import enactor.brs.constant.Constants;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.request.ReservationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationService {

    private static final Logger logger = LogManager.getLogger(ValidationService.class);

    /**
     * Method to validate availability requests
     */
    public void validateAvailability(AvailabilityRequest availabilityRequest)
            throws IllegalArgumentException {

            int passengers = availabilityRequest.getPassengers();
            String origin = availabilityRequest.getOrigin();
            String destination = availabilityRequest.getDestination();

            if (passengers <= 0) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.INVALID_PASSENGERS.getMessage());
                throw new IllegalArgumentException(ErrorMessage.INVALID_PASSENGERS.getMessage());
            }

            if (origin == null || origin.trim().isEmpty()) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.ORIGIN_REQUIRED.getMessage());
                throw new IllegalArgumentException(ErrorMessage.ORIGIN_REQUIRED.getMessage());
            }

            if (!Constants.VALID_LOCATIONS.contains(origin.trim().toUpperCase())) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.INVALID_ORIGIN.getMessage());
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORIGIN.getMessage());
            }

            if (destination == null || destination.trim().isEmpty()) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.DESTINATION_REQUIRED.getMessage());
                throw new IllegalArgumentException(ErrorMessage.DESTINATION_REQUIRED.getMessage());
            }

            if (!Constants.VALID_LOCATIONS.contains(destination.trim().toUpperCase())) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.INVALID_DESTINATION.getMessage());
                throw new IllegalArgumentException(ErrorMessage.INVALID_DESTINATION.getMessage());
            }

            if (origin.equalsIgnoreCase(destination)) {
                logger.error("Error in validateAvailability:{}",ErrorMessage.SAME_LOCATION.getMessage());
                throw new IllegalArgumentException(ErrorMessage.SAME_LOCATION.getMessage());
            }
        }

    /**
     * Method to validate reservation requests
     */
    public void validateReservation(ReservationRequest reservationRequest)
            throws IllegalArgumentException {
        int passengers = reservationRequest.getPassengers();
        String origin = reservationRequest.getOrigin();
        String destination = reservationRequest.getDestination();
        double confirmPrice  = reservationRequest.getConfirmPrice();

        if (passengers <= 0) {
            logger.error("Error in validateReservation:{}",ErrorMessage.INVALID_PASSENGERS.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSENGERS.getMessage());
        }

        if (origin == null || origin.trim().isEmpty()) {
            logger.error("Error in validateReservation:{}",ErrorMessage.ORIGIN_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ORIGIN_REQUIRED.getMessage());
        }

        if (!Constants.VALID_LOCATIONS.contains(origin.trim().toUpperCase())) {
            logger.error("Error in validateReservation:{}",ErrorMessage.INVALID_ORIGIN.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORIGIN.getMessage());
        }

        if (destination == null || destination.trim().isEmpty()) {
            logger.error("Error in validateReservation:{}",ErrorMessage.DESTINATION_REQUIRED.getMessage());
            throw new IllegalArgumentException(ErrorMessage.DESTINATION_REQUIRED.getMessage());
        }

        if (!Constants.VALID_LOCATIONS.contains(destination.trim().toUpperCase())) {
            logger.error("Error in validateReservation:{}",ErrorMessage.INVALID_DESTINATION.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_DESTINATION.getMessage());
        }

        if (origin.equalsIgnoreCase(destination)) {
            logger.error("Error in validateReservation:{}",ErrorMessage.SAME_LOCATION.getMessage());
            throw new IllegalArgumentException(ErrorMessage.SAME_LOCATION.getMessage());
        }

        if (confirmPrice <= 0) {
            logger.error("Error in validateReservation:{}",ErrorMessage.INVALID_CONFIRM_PRICE.getMessage());
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONFIRM_PRICE.getMessage());
        }
    }
}
