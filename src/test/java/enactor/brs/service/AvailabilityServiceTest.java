package enactor.brs.service;

import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.response.AvailabilityResponse;
import enactor.brs.store.InMemoryStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityServiceTest {

    private final AvailabilityService service = new AvailabilityService();

    @BeforeEach
    void setup() {
        InMemoryStore.getInstance().reset();
    }

    /**
     * Initially all 40 seats should be available
     */
    @Test
    void testInitialAvailability() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(1);
        req.setOrigin("A");
        req.setDestination("C");

        AvailabilityResponse res = service.checkAvailability(req);

        assertEquals(40, res.getAvailableSeats());
    }

    /**
     * When passengers exceeded available sheets
     */
    @Test
    void testAvailabilityWithExceededPassengers() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(41);
        req.setOrigin("A");
        req.setDestination("C");

        AvailabilityResponse res = service.checkAvailability(req);

        assertNotNull(res);
        assertTrue(res.getAvailableSeats() <= 40);
    }

    /**
     * Price calculation should be correct
     */
    @Test
    void testPriceCalculation() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(2);
        req.setOrigin("A");
        req.setDestination("C");

        AvailabilityResponse res = service.checkAvailability(req);

        assertEquals(200.0, res.getTotalPrice());
    }

    /**
     * Lowercase input should still work
     */
    @Test
    void testLowerCaseRoute() {
        AvailabilityRequest req = new AvailabilityRequest();
        req.setPassengers(1);
        req.setOrigin("a");
        req.setDestination("c");

        AvailabilityResponse res = service.checkAvailability(req);

        assertEquals(100.0, res.getTotalPrice());
    }
}