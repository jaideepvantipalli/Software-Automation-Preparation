import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedRead {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("input.txt");
        BufferedInputStream bis=new BufferedInputStream(fis);

        int data;

        while((data = bis.read()) != -1){
            System.out.print((char) data);
        }
        bis.close();
    }
}
