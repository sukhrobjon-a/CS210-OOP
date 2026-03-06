import java.util.ArrayList;

class Member extends Person {

    ArrayList<String> borrowedBooks = new ArrayList<>();

    Member(String name, int id, int age) {
        super(name, id, age);
    }

    void borrowBook(String book) {
        borrowedBooks.add(book);
        System.out.println(name + " borrowed: " + book);
    }

    void returnBook(String book) {
        if (borrowedBooks.remove(book)) {
            System.out.println(name + " returned: " + book);
        } else {
            System.out.println("Book was not borrowed.");
        }
    }

    void viewBorrowedBooks() {
        System.out.println("Borrowed Books:");
        for (String book : borrowedBooks) {
            System.out.println(book);
        }
    }
}