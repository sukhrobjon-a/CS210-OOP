public class FlightSeat extends Seat {

    double fare;

    public FlightSeat(String seatNumber, String seatClass, double fare) {
        super(seatNumber, seatClass);
        this.fare = fare;
    }

    public double getFare() {
        return fare;
    }
}