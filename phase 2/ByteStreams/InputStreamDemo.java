import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class InputStreamDemo {
    public static void main(String args[])  throws IOException{
        InputStream ip=new FileInputStream("input.txt");
        int data;

        while((data = ip.read()) !=-1){
            System.out.print((char) data);
        }
        ip.close();
    }
}
