import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextFileCopy{
    public static void main(String args[]) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("student.txt"));
        
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("destination.txt", StandardCharsets.UTF_8)
            );
        
        String line;
        while((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
        }
        System.out.println("File Copied Succesfully");
        br.close();
        bw.close();
    }
}