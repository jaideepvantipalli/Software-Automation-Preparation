import java.io.*;
import java.nio.charset.StandardCharsets;

public class OutputStreamWriterExample {
    public static void main(String[] args) {

        try (
            OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("output.txt"),
                StandardCharsets.UTF_8
            )
        ) {

            osw.write("Java File Handling");
            osw.write("\n");
            osw.write("Character Streams");

            System.out.println("Data written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}