import java.io.FileReader;
import java.io.IOException;
public class FileReaderDemo {
    public static void main(String[] args) throws IOException{
        FileReader fr=new FileReader("student.txt");

        int data;

        while((data = fr.read()) != -1){
            System.out.print((char) data);
        }
        fr.close();
    }
}
