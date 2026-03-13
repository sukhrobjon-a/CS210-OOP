import java.util.*;

public class Customer extends Person {

    String frequentFlyerNumber;
    List<FlightReservation> reservations = new ArrayList<>();

    public Customer(String name, String email, String phone, String number) {
        super(name, email, phone);
        this.frequentFlyerNumber = number;
    }

    public void addReservation(FlightReservation r) {
        reservations.add(r);
    }
}