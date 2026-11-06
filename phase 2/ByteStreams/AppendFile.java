import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AppendFile {
    public static void main(String[] args) {

        String message = "\nLearning Buffered Streams";

        try (FileOutputStream fos =
                new FileOutputStream("output.txt", true)) {

            fos.write(message.getBytes(StandardCharsets.UTF_8));

            System.out.println("Data appended successfully");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}