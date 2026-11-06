import java.io.FileWriter;
import java.io.IOException;
public class FileWriterDemo {
    public static void main(String[] args) throws IOException{
        FileWriter fw=new FileWriter("output.txt");

        String msg="Hi learning FileWriter";

        fw.write(msg);
        System.out.print("Data Written Successfully");

        fw.close();
    }
}
