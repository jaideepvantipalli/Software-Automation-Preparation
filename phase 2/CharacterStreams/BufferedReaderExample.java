import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class BufferedReaderExample {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("student.txt"));

        String line;

        while((line = br.readLine()) != null){
            System.err.println(line);
        }
        br.close();
    }
}
