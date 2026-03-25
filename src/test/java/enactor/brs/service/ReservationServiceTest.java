package enactor.brs.service;

import enactor.brs.model.request.ReservationRequest;
import enactor.brs.model.response.ReservationResponse;
import enactor.brs.store.InMemoryStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private final ReservationService service = new ReservationService();

    @BeforeEach
    void setup() {
        InMemoryStore.getInstance().reset();
    }

    /**
     * Successful reservation should allocate seats
     */
    @Test
    void testSuccessfulReservation() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(2);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(200.0);

        ReservationResponse res = service.reserveSeats(req);

        assertEquals("1", res.getStatus());
        assertEquals(2, res.getSeats().size());
        assertNotNull(res.getTicketNumber());
    }

    /**
     * Price mismatch should fail
     */
    @Test
    void testPriceMismatch() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(2);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(100.0);

        ReservationResponse res = service.reserveSeats(req);

        assertEquals("-1", res.getStatus());
    }

    /**
     * Booking all seats should succeed
     */
    @Test
    void testFullBooking() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(40);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(4000.0);

        ReservationResponse res = service.reserveSeats(req);

        assertEquals("1", res.getStatus());
    }

    /**
     * Booking after full capacity should fail
     */
    @Test
    void testOverBooking() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(40);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(4000.0);

        service.reserveSeats(req);

        ReservationResponse res = service.reserveSeats(req);

        assertEquals("-1", res.getStatus());
    }

    /**
     * Ticket numbers should be unique
     */
    @Test
    void testTicketUniqueness() {
        ReservationRequest req = new ReservationRequest();
        req.setPassengers(1);
        req.setOrigin("A");
        req.setDestination("C");
        req.setConfirmPrice(100.0);

        String t1 = service.reserveSeats(req).getTicketNumber();
        String t2 = service.reserveSeats(req).getTicketNumber();

        assertNotEquals(t1, t2);
    }
}