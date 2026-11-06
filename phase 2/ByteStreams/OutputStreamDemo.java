import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class OutputStreamDemo {
    public static void main(String args[])  throws IOException{
        OutputStream op=new FileOutputStream("output2.txt");
        String message = "Learning OutputStream";

            byte[] data =
                message.getBytes(StandardCharsets.UTF_8);

            op.write(data);

            System.out.println("Data written successfully");

            op.close();
    }
}