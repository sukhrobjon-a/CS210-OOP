public class Passenger {

    String name;
    String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return name + " (" + passportNumber + ")";
    }
}