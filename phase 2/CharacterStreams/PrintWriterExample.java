import java.io.*;
import java.nio.charset.StandardCharsets;

public class PrintWriterExample {
    public static void main(String[] args) {

        try (PrintWriter pw = new PrintWriter(
                "student.txt", StandardCharsets.UTF_8)) {

            pw.println("Name: Jaideep");
            pw.println("Course: B.Tech CSE");
            pw.println("College: Sri Vasavi Engineering College");

            System.out.println("Data written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}