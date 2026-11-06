import java.io.*;
import java.nio.charset.StandardCharsets;

public class KeyboardInputExample {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8)
        );

        System.out.print("Enter your name: ");
        String name = br.readLine();

        System.out.print("Enter your college: ");
        String college = br.readLine();

        System.out.println("Name: " + name);
        System.out.println("College: " + college);
    }
}