import java.util.ArrayList;

class Guest extends Person {

    Guest(String name, int id, int age) {
        super(name, id, age);
    }

    void viewCatalog(ArrayList<String> library) {
        System.out.println("Library Catalog:");
        for (String book : library) {
            System.out.println(book);
        }
    }
}