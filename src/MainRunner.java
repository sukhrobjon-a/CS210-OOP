public class MainRunner {

    public static void main(String[] args) {

        Airport tashkent = new Airport("Tashkent Airport", "TAS");
        Airport dubai = new Airport("Dubai Airport", "DXB");

        Flight flight = new Flight("HY101", tashkent, dubai);

        FlightInstance instance = new FlightInstance(flight);

        Passenger p1 = new Passenger("Sukhrob", "FA6777763");

        FlightSeat seat = new FlightSeat("12A", "Economy", 350);

        FlightReservation reservation =
                new FlightReservation("R001", instance);

        reservation.addPassenger(p1, seat);

        Payment payment = new Payment();
        payment.processPayment(seat.getFare());

        System.out.println("Reservation completed.");
    }
}