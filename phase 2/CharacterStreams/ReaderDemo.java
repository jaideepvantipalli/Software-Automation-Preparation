import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
public class ReaderDemo{
    public static void main(String[] args) throws IOException {
        Reader reader=new FileReader("student.txt");
        int data;

        while((data = reader.read()) != -1){
            System.out.print((char) data);
        }
        reader.close();
    }
}