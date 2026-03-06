class Book extends Product {
    String author;
    String ISBN;

    Book(int productID, String name, double price, String author, String ISBN) {
        super(productID, name, price);
        this.author = author;
        this.ISBN = ISBN;
    }

    void getAuthorInfo() {
        System.out.println(name + " is written by " + author + " (ISBN: " + ISBN + ").");
    }
}