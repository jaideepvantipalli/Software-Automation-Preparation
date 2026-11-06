import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {

        String s="Hello Deepu";
        try (FileOutputStream fos =
                     new FileOutputStream("input.txt")) {

            byte[] data=s.getBytes();

            fos.write(data);
            System.out.println("Data Written succesfully");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}