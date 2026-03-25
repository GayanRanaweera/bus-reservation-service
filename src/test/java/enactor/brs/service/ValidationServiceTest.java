package enactor.brs.service;

import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.request.ReservationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    /**
     * Valid request should pass without exception
     */
    @Test
    void testValidAvailability() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(2);
        req.setOrigin("A");
        req.setDestination("C");

        assertDoesNotThrow(() -> validationService.validateAvailability(req));
    }

    /**
     * Passengers <= 0 should fail
     */
    @Test
    void testInvalidPassengers() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(0);
        req.setOrigin("A");
        req.setDestination("C");

        assertThrows(IllegalArgumentException.class,
                () -> validationService.validateAvailability(req));
    }

    /**
     * Null origin should fail
     */
    @Test
    void testNullOrigin() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(1);
        req.setOrigin(null);
        req.setDestination("C");

        assertThrows(IllegalArgumentException.class,
                () -> validationService.validateAvailability(req));
    }

    /**
     * Same origin and destination should fail
     */
    @Test
    void testSameLocation() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(1);
        req.setOrigin("A");
        req.setDestination("A");

        assertThrows(IllegalArgumentException.class,
                () -> validationService.validateAvailability(req));
    }

    /**
     * Lowercase input should still pass (if normalized)
     */
    @Test
    void testLowerCaseInput() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(1);
        req.setOrigin("a");
        req.setDestination("c");

        assertDoesNotThrow(() -> validationService.validateAvailability(req));
    }

    /**
     * Reservation validation success
     */
    @Test
    void testValidReservation() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(2);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(200.0);

        assertDoesNotThrow(() -> validationService.validateReservation(req));
    }
}