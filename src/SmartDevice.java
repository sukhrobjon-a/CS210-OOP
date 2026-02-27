class SmartDevice {
    private String brand;
    private boolean isPowerOn;

    public SmartDevice(String brand, boolean isPowerOn) {
        this.brand = brand;
        this.isPowerOn = isPowerOn;
    }

    public void turnOn() {
        isPowerOn = true;
    }

    public void turnOff() {
        isPowerOn = false;
    }

    public String getStatus() {
        return isPowerOn ? "ON" : "OFF";
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", Power Status: " + getStatus();
    }
}