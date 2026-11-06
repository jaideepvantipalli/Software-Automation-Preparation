import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordCount {
    public static void main(String[] args) {

        int words = 0;

        try (BufferedReader br = new BufferedReader(
                new FileReader("student.txt", StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (!line.isEmpty()) {
                    words += line.split("\\s+").length;
                }
            }

            System.out.println("Total Words: " + words);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}