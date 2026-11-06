import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedWrite {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("output.txt",true);
        BufferedOutputStream bos=new BufferedOutputStream(fos);

        String message="Learning Java BufferedOutput streams";

        byte[] data=message.getBytes();

        bos.write(data);

        bos.flush();

        System.out.print("File Written Successfully");
        bos.close();
    }
}
