import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentService {
    private final StudentFileHandler fileHandler;
    private final BufferedReader keyboard;

    public StudentService() {
        fileHandler = new StudentFileHandler();
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addRecord() throws IOException {
        int rollNumber = readInteger("Enter Roll No:");
        if(fileHandler.rollExists(rollNumber)){
            System.out.println("Record already exists!");
            return;
        }
        Student student = record(rollNumber);
        fileHandler.addStudent(student);
    }

    public void readAllRecords() throws IOException {
        fileHandler.readAllStudents();
    }

    public void searchByRollNumber() throws IOException{
        int rollNumber = readInteger("Enter Roll No:");
        fileHandler.searchByRollNumber(rollNumber);
    }

    public void searchByName() throws IOException{

        System.out.println("Enter Student Name:");
        String name=keyboard.readLine().trim();
        fileHandler.searchByName(name);
    }

    public void deleteRecord() throws IOException{
        int rollNumber = readInteger("Enter Roll No:");
        fileHandler.deleteRecord(rollNumber);
    }

    public void updateRecord() throws IOException{
        int rollNumber = readInteger("Enter Roll No:");
        if(!fileHandler.rollExists(rollNumber)){
            System.out.println("Record Not Found!");
            return;
        }
        System.out.println("Enter Updated Student Details :");
        Student student=record(rollNumber);
        fileHandler.updateRecord(rollNumber,student);
    }

    public Student record (int rollNumber) throws IOException {
        int age=readInteger("Enter Age:");
        System.out.println("Enter Name:");
        String name=keyboard.readLine().trim();
        if(name.isEmpty() || name.contains("|")) throw new IllegalArgumentException("Invalid Name!");

        double m1=readDouble("Enter M1 Marks:");
        double m2=readDouble("Enter M2 Marks:");
        double m3=readDouble("Enter M3 Marks:");
        double m4=readDouble("Enter M4 Marks:");
        double m5=readDouble("Enter M5 Marks:");
        return new Student(rollNumber,age,name,m1,m2,m3,m4,m5);
    }

    public int readInteger(String message) throws IOException {
        while(true){
            try{
                System.out.println(message);
                return Integer.parseInt(keyboard.readLine());
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
    }

    public double readDouble(String msg) throws IOException{
        while(true){
            try{
                System.out.println(msg);
                double marks = Double.parseDouble(keyboard.readLine());
                if(marks >=0 && marks <=100) return marks;
                System.out.println("Marks should be between 0 and 100!");
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
    }
}
