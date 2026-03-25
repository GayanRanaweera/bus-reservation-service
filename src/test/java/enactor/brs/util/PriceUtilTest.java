package enactor.brs.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceUtilTest {

    /**
     * Should return correct price for A → C
     */
    @Test
    void testPriceForwardRoute() {
        assertEquals(100.0, PriceUtil.getPricePerSeat("A", "C"));
    }

    /**
     * Should support reverse route (C → A)
     */
    @Test
    void testPriceReverseRoute() {
        assertEquals(100.0, PriceUtil.getPricePerSeat("C", "A"));
    }

    /**
     * Invalid route should return 0
     */
    @Test
    void testInvalidRoute() {
        assertEquals(0.0, PriceUtil.getPricePerSeat("A", "X"));
    }

    /**
     * Same route should return 0
     */
    @Test
    void testSameRoute() {
        assertEquals(0.0, PriceUtil.getPricePerSeat("A", "A"));
    }
}