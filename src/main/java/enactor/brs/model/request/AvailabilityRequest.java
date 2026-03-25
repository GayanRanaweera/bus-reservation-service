package enactor.brs.model.request;

public class AvailabilityRequest {
    private Integer passengers;
    private String origin;
    private String destination;

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "AvailabilityRequest{" +
                "passengers=" + passengers +
                ", origin='" + origin +
                ", destination='" + destination +
                '}';
    }
}
