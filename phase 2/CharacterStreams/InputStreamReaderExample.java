import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamReaderExample {
    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(
            System.in, StandardCharsets.UTF_8
        );

        System.out.print("Enter a character: ");

        int data = isr.read();

        System.out.println("Character: " + (char) data);

        isr.close();
    }
}