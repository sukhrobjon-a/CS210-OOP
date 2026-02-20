public class Sensor {
    private String location;
    private double reading = 0.0;

    public Sensor(String location, double reading) {
        if (reading >= -50.0 && reading <= 50.0)
            this.reading = reading;
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public double getReading() {
        return reading;
    }
}
