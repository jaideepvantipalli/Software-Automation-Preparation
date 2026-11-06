import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputDemo {

    public static void main(String[] args) {

        try (
            DataInputStream dis =
                new DataInputStream(
                    new FileInputStream("student.dat")
                );

                
        ) {
System.out.println("hello");
            int id = dis.readInt();
            String name = dis.readUTF();
            double cgpa = dis.readDouble();
            boolean passed = dis.readBoolean();
            char grade = dis.readChar();

            System.out.println("Student ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("CGPA: " + cgpa);
            System.out.println("Passed: " + passed);
            System.out.println("Grade: " + grade);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}