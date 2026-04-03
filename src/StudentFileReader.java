import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentFileReader {
    public static void main(String[] args) {

        int count = 0;

        try {
            File file = new File("students.txt");
            Scanner scanner = new Scanner(file);

            System.out.println("Name\t\tGPA\tMajor");
            System.out.println("--------------------------------------");

            while (scanner.hasNextLine()) {
                String first = scanner.next();
                String last = scanner.next();
                String gpa = scanner.next();
                String major = scanner.next();

                System.out.println(first + " " + last + "\t" + gpa + "\t" + major);
                count++;
            }

            System.out.println("\nTotal students: " + count);

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt file not found.");
        }
    }
}