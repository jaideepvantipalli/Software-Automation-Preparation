import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedWriterExample {
    public static void main(String[] args) {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("output.txt", StandardCharsets.UTF_8))) {

            bw.write("Java Programming");
            bw.newLine();

            bw.write("Python Programming");
            bw.newLine();

            bw.write("C Programming");

            System.out.println("Data written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}