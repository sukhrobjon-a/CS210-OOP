import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class StudentFileWriter {
    public static void main(String[] args) {

        File file = new File("students.txt");

        if (file.exists()) {
            System.out.println("Warning: File already exists.");
        }

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.println("Ali Karimov 3.85 ComputerScience");
            writer.println("Malika Aliyeva 3.90 Mathematics");
            writer.println("Aziz Tursunov 3.70 Physics");
            writer.println("Sardor Xasanov 3.60 Engineering");
            writer.println("Nodira Rahimova 3.95 IT");

            System.out.println("File written successfully.");

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}