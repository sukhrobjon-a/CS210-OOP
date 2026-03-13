import java.util.*;

public class FlightReservation {

    String reservationNumber;
    FlightInstance flight;
    Map<Passenger, FlightSeat> seatMap = new HashMap<>();

    public FlightReservation(String number, FlightInstance flight) {
        this.reservationNumber = number;
        this.flight = flight;
    }

    public void addPassenger(Passenger p, FlightSeat seat) {
        seatMap.put(p, seat);
    }
}