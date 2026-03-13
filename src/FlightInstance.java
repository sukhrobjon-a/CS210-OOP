public class FlightInstance {

    Flight flight;
    String status;

    public FlightInstance(Flight flight) {
        this.flight = flight;
        this.status = "Scheduled";
    }

    public void cancel() {
        status = "Cancelled";
    }
}