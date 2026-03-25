package enactor.brs.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryStoreTest {

    /**
     * Singleton instance should always be same
     */
    @Test
    void testSingleton() {
        InMemoryStore s1 = InMemoryStore.getInstance();
        InMemoryStore s2 = InMemoryStore.getInstance();

        assertSame(s1, s2);
    }

    /**
     * Should initialize with 40 seats
     */
    @Test
    void testSeatInitialization() {
        InMemoryStore store = InMemoryStore.getInstance();
        assertEquals(40, store.getAllSeats().size());
    }
}