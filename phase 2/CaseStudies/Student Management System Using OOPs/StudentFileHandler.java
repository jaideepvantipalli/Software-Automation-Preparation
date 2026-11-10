import java.io.*;
import java.nio.Buffer;

public class StudentFileHandler {
    private final File studentFile;
    private final File tempFile;
    public StudentFileHandler() {
        studentFile = new File("student.txt");
        tempFile = new File("temp.txt");
    }
    public boolean rollExists(int rollno) throws IOException {
        if(!studentFile.exists()){ return false;}

        try(BufferedReader br = new BufferedReader(new FileReader(studentFile))){
            String line;
            while((line= br.readLine()) != null){
                Student student=Student.fromFileRecord(line);
                if(student.getRollNumber()==rollno){
                    return true;
                }
            }
        }
        return false;
    }

    public void addStudent(Student student) throws IOException {
        if(rollExists(student.getRollNumber())){
            System.out.println("ROll no already Exists");
            return;
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(studentFile,true))) {
            bw.write(student.toFileRecord());
            bw.newLine();
            System.out.println("Student Saved Successfully.");
        }
    }

    public void readAllStudents() throws IOException{
        if(!studentFile.exists()){
            System.out.println("No Student Record Found");
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(studentFile))){
            String line;
            while((line = br.readLine()) != null){
                Student student = Student.fromFileRecord(line);
                student.displayDetails();
            }
        }
    }

    public void searchByRollNumber(int rollno) throws IOException{
        if(!studentFile.exists()){
            System.out.println("No Student Record Found");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(studentFile))){
            String line;
            while((line = br.readLine()) != null){
                Student student = Student.fromFileRecord(line);
                if(student.getRollNumber() == rollno){
                    student.displayDetails();
                    return;
                }
            }
        }
    }

    public void searchByName(String name) throws IOException{
        if(!studentFile.exists()){
            System.out.println("No Student Record Found");
            return;
        }
        boolean found=false;
        try(BufferedReader br = new BufferedReader(new FileReader(studentFile))){
            String line;

            while((line = br.readLine()) != null){
                Student student = Student.fromFileRecord(line);
                if(student.getName().equalsIgnoreCase(name)){
                    student.displayDetails();
                    found=true;
                }
            }
        }
        if(!found){
            System.out.println("No Student Record Found");
        }
    }

    public void deleteRecord(int rollNumber) throws IOException{
        if(!studentFile.exists()){
            System.out.println("No Student Record Found");
            return;
        }
        boolean found = false;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile,true));
        BufferedReader br = new BufferedReader(new FileReader(studentFile))) {

            String line;
            while((line = br.readLine()) != null){
                Student student = Student.fromFileRecord(line);
                if(student.getRollNumber() == rollNumber){
                    found = true;
                }else{
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        if(!found){
            tempFile.delete();
            System.out.println("Student not Found");
            return;
        }
        replaceOriginalFile();
        System.out.println("Student Record Deleted Successfully.");
    }

    public void updateRecord(int rollNumber,Student UpdatedStudent) throws IOException{

        if(!studentFile.exists()){
            System.out.println("No student Record Found");
            return;
        }

        boolean found = false;
        try(BufferedReader br=new BufferedReader(new FileReader(studentFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))){
            String line;

            while((line = br.readLine()) != null){
                Student student = Student.fromFileRecord(line);

                if(UpdatedStudent.getRollNumber() == rollNumber){
                    bw.write(UpdatedStudent.toFileRecord());
                    found = true;
                }else{
                    bw.write(line);
                }
                bw.newLine();
            }
        }
        if(!found){
            tempFile.delete();
            System.out.println("Student not Found");
            return;
        }
        replaceOriginalFile();
        System.out.println("Student Record Updated Successfully.");
    }

    public void replaceOriginalFile() throws IOException{
        if(!studentFile.delete()){
            tempFile.delete();
            throw new IOException("unable to delete original File");
        }
        if(!tempFile.renameTo(studentFile)){
            throw new IOException("unable to rename original File");
        }
    }
}