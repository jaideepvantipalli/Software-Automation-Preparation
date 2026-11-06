import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class FileCopy{
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("source.jpg"));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("dest.jpg"));

        byte[] data=new byte[8192];

        int byteRead;

        while((byteRead = bis.read(data)) != -1){
            bos.write(data,0,byteRead);
        }
        bis.close();
        bos.close();
        System.out.println("File Copied Successfully");
    }
}