import java.io.FileReader;
import java.io.IOException;
public class FilereaderArray {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("student.txt");

        char[] buffer = new char[1024];
        int data;

        while((data = fr.read(buffer)) != -1){
            System.out.print(new String(buffer,0,data));
        }
        fr.close();
    }
}
