import java.util.ArrayList;

public class LibrarySystem {

    public static void main(String[] args) {

        ArrayList<String> library = new ArrayList<>();

        library.add("Java");
        library.add("AI");
        library.add("Operating Systems");

        Librarian librarian = new Librarian("NewUU Librarian", 101, 35);
        Member member = new Member("Sukhrobjon", 250326, 18);
        Guest guest = new Guest("Abdulhamid", 250277, 19);

        librarian.addBook(library, "Calculus 2");

        guest.viewCatalog(library);

        librarian.issueBook(member, "Calculus 2");

        member.viewBorrowedBooks();

        member.returnBook("Java");
    }
}