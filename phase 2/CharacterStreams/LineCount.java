import java.io.*;
import java.nio.charset.StandardCharsets;

public class LineCount {
    public static void main(String[] args) {

        int count = 0;

        try (BufferedReader br = new BufferedReader(
                new FileReader("student.txt", StandardCharsets.UTF_8))) {

            while (br.readLine() != null) {
                count++;
            }

            System.out.println("Total Lines: " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}