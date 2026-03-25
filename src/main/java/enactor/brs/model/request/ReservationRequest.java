package enactor.brs.model.request;

public class ReservationRequest {
    private Integer passengers;
    private String origin;
    private String destination;
    private Double confirmPrice;

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

    public Double getConfirmPrice() {
        return confirmPrice;
    }

    public void setConfirmPrice(Double confirmPrice) {
        this.confirmPrice = confirmPrice;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "passengers=" + passengers +
                ", origin='" + origin +
                ", destination='" + destination +
                ", confirmPrice=" + confirmPrice +
                '}';
    }
}