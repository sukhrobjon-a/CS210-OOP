public class Flight {

    String flightNumber;
    Airport departure;
    Airport arrival;

    public Flight(String flightNumber, Airport departure, Airport arrival) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
    }
}