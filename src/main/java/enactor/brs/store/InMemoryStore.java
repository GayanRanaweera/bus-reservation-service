package enactor.brs.store;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore {

    // Singleton instance
    private static final InMemoryStore INSTANCE = new InMemoryStore();

    // All seats (fixed)
    private final List<String> allSeats = new ArrayList<>();

    // Booked seats (thread-safe)
    private final Set<String> bookedSeats = ConcurrentHashMap.newKeySet();

    // Ticket → Seats mapping
    private final ConcurrentHashMap<String, List<String>> ticketSeatMap =
            new ConcurrentHashMap<>();

    // Private constructor (Singleton)
    private InMemoryStore() {
        initializeSeats();
    }

    // Get instance
    public static InMemoryStore getInstance() {
        return INSTANCE;
    }

    // Initialize seats (1A–10D)
    private void initializeSeats() {
        for (int i = 1; i <= 10; i++) {
            for (char c = 'A'; c <= 'D'; c++) {
                allSeats.add(i + String.valueOf(c));
            }
        }
    }

    // Get all seats
    public List<String> getAllSeats() {
        return allSeats;
    }

    // Get booked seats
    public Set<String> getBookedSeats() {
        return bookedSeats;
    }

    // Get ticket map
    public ConcurrentHashMap<String, List<String>> getTicketSeatMap() {
        return ticketSeatMap;
    }

    // Utility: get available seats
    public List<String> getAvailableSeats() {
        List<String> available = new ArrayList<>();

        for (String seat : allSeats) {
            if (!bookedSeats.contains(seat)) {
                available.add(seat);
            }
        }

        return available;
    }

    // Utility: clear all data (useful for testing)
    public void reset() {
        bookedSeats.clear();
        ticketSeatMap.clear();
    }
}