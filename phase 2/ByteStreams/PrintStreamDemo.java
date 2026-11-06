import java.io.PrintStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PrintStreamDemo {

    public static void main(String[] args) {

        try (
            PrintStream ps =
                new PrintStream(
                    "student.txt",
                    StandardCharsets.UTF_8
                )
        ) {

            String name = "Jaideep";
            int id = 101;
            double cgpa = 8.8;
            boolean passed = true;

            ps.println("Student Details");
            ps.println("----------------");
            ps.println("Name: " + name);
            ps.println("ID: " + id);
            ps.printf("CGPA: %.2f%n", cgpa);
            ps.println("Passed: " + passed);

            if (ps.checkError()) {
                System.out.println("An output error occurred");
            } else {
                System.out.println("Data written successfully");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}