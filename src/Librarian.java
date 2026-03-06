import java.util.ArrayList;

class Librarian extends Person {

    Librarian(String name, int id, int age) {
        super(name, id, age);
    }

    void addBook(ArrayList<String> library, String book) {
        library.add(book);
        System.out.println("Book added: " + book);
    }

    void removeBook(ArrayList<String> library, String book) {
        if (library.remove(book)) {
            System.out.println("Book removed: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }

    void issueBook(Member member, String book) {
        member.borrowBook(book);
    }
}