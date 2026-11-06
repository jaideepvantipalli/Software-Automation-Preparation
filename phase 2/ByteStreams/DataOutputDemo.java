import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputDemo {

    public static void main(String[] args) {

        try (
            DataOutputStream dos =
                new DataOutputStream(
                    new FileOutputStream("student.dat")
                )
        ) {

            int id = 101;
            String name = "Jaideep";
            double cgpa = 8.8;
            boolean passed = true;
            char grade = 'A';

            dos.writeInt(id);
            dos.writeUTF(name);
            dos.writeDouble(cgpa);
            dos.writeBoolean(passed);
            dos.writeChar(grade);

            dos.flush();

            System.out.println("Student data saved successfully");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}