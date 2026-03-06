class Clothing extends Product {
    String size;
    String color;

    Clothing(int productID, String name, double price, String size, String color) {
        super(productID, name, price);
        this.size = size;
        this.color = color;
    }

    void checkSizeAvailability() {
        System.out.println("The " + name + " is available in size " + size + " and color " + color + ".");
    }
}