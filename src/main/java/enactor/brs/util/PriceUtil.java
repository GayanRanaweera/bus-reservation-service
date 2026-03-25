package enactor.brs.util;

import java.util.HashMap;
import java.util.Map;

public class PriceUtil {

    /**
     * Map to store route → price
     */
    private static final Map<String, Double> PRICE_MAP = new HashMap<>();

    /**
     * Static initializer block
     * Loads all route prices once at class loading time
     */
    static {
        PRICE_MAP.put("A-B", 50.0);
        PRICE_MAP.put("A-C", 100.0);
        PRICE_MAP.put("A-D", 150.0);
        PRICE_MAP.put("B-C", 50.0);
        PRICE_MAP.put("B-D", 100.0);
        PRICE_MAP.put("C-D", 50.0);
    }

    /**
     * Get price per seat for given route
     */
    public static double getPricePerSeat(String origin, String destination) {

        if (origin == null || destination == null) {
            return 0.0;
        }

        origin = origin.trim().toUpperCase();
        destination = destination.trim().toUpperCase();

        String key = origin + "-" + destination;
        String reverseKey = destination + "-" + origin;

        return PRICE_MAP.getOrDefault(key, PRICE_MAP.getOrDefault(reverseKey, 0.0));
    }
}