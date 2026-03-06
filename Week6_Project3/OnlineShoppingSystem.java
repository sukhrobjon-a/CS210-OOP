public class OnlineShoppingSystem {
    public static void main(String[] args) {

        Electronics e1 = new Electronics(101, "Smartphone", 699.99, 24, "Samsung");
        Clothing c1 = new Clothing(201, "T-Shirt", 19.99, "M", "Red");
        Book b1 = new Book(301, "Java Programming", 39.99, "John Doe", "123-4567890123");

        e1.displayProductDetails();
        e1.getWarrantyDetails();

        System.out.println();

        c1.displayProductDetails();
        c1.checkSizeAvailability();

        System.out.println();

        b1.displayProductDetails();
        b1.getAuthorInfo();
    }
}