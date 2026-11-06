import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {

        try (FileInputStream fis =
                     new FileInputStream("input.txt")) {

            int data;

            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}