import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterDemo {
    public static void main(String[] args) throws IOException {
        Writer writer=new FileWriter("output.txt");

        String msg = "hi hello this is writer class";

        writer.write(msg);
        System.out.println("Data Written successfully");
        writer.close();
    }
}
