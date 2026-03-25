package enactor.brs.model.response;

import java.util.List;

public class ReservationResponse {
    private String ticketNumber;
    private List<String> seats;
    private String origin;
    private String destination;
    private double totalPrice;
    private String status;
    private String reason;


    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "ticketNumber='" + ticketNumber + 
                ", seats=" + seats +
                ", origin='" + origin + 
                ", destination='" + destination + 
                ", totalPrice=" + totalPrice +
                ", status='" + status + 
                ", reason='" + reason + 
                '}';
    }
}
