import java.io.*;

public class StudentGradingSystem {
    static final String FileName= "student.txt";

    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int choice;
        try{
            while(true) {
                System.out.println("\n================================");
                System.out.println("   STUDENT MANAGEMENT SYSTEM");
                System.out.println("================================");
                System.out.println("1. Add Student Record");
                System.out.println("2. Read All Student Records");
                System.out.println("3. Search Student By Roll Number");
                System.out.println("4. Search Student By Name");
                System.out.println("5. Delete Student By Roll Number");
                System.out.println("6. Update Student By Roll Number");
                System.out.println("0. Exit");

                System.out.println("================================");

                choice = readInteger("Enter Your Choice: ");

                switch (choice) {

                    case 1:
                        addRecord();
                        break;

                    case 2:
                        readAllRecords();
                        break;

                    case 3:
                        searchByRoll();
                        break;

                    case 4:
                        searchByName();
                        break;

                    case 5:
                        deleteRecord();
                        break;

                    case 6:
                        updateRecord();
                        break;

                    case 0:
                        System.out.println("Exiting Application...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    static void deleteRecord() throws IOException {
        File originalFile = new File(FileName);
        File tempFile = new File("Temp.txt");

        int rollNumber = readInteger("Enter Roll no to delete the student Record:");
        boolean recordFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (Integer.parseInt(data[0]) != rollNumber) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    recordFound = true;
                }
            }
        }

        if (!recordFound) {
            tempFile.delete();
            System.out.println("Student record with Roll No " + rollNumber + " not found.");
            return;
        }

        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Failed to rename Temp.txt to " + FileName);
            }
            System.out.println("Record deleted successfully.");
        } else {
            tempFile.delete();
            throw new IOException("Failed to delete " + FileName + " to complete update.");
        }
    }

    static void updateRecord() throws IOException{
        File originalFile = new File(FileName);
        File tempFile = new File("Temp.txt");
        int rollNumber = readInteger("Enter Roll no to update the student Record: ");
        boolean recordFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FileName));
            BufferedWriter bw= new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data=line.split("\\|");
                if (Integer.parseInt(data[0]) != rollNumber) {
                    bw.write(line);
                    bw.newLine();
                }else {
                    recordFound = true;
                    System.out.println("Student Record with Existing Details :");
                    DisplayStudentDetails(data);

                    int age=readInteger("Enter Age: ");

                    System.out.print("Enter Name: ");
                    String name=keyboard.readLine().trim();
                    if(name.isEmpty()){
                        System.out.println("Invalid Name!");
                        return;
                    }
                    double m1=readDouble("Enter sub1 marks :");
                    double m2=readDouble("Enter sub2 marks :");
                    double m3=readDouble("Enter sub3 marks :");
                    double m4=readDouble("Enter sub4 marks :");
                    double m5=readDouble("Enter sub5 marks :");

                    String grade= calcGrade(m1,m2,m3,m4,m5);
                    String record = createRecord(Integer.parseInt(data[0]),age,name,m1,m2,m3,m4,m5,grade);

                    bw.write(record);
                    bw.newLine();
                    System.out.println("Student Record has been Updated successfully!");
                }
            }
        }
        if (!recordFound) {
            tempFile.delete();
            System.out.println("Student record with Roll No " + rollNumber + " not found.");
            return;
        }

        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Failed to rename Temp.txt to " + FileName);
            }
            System.out.println("Record deleted successfully.");
        } else {
            tempFile.delete();
            throw new IOException("Failed to delete " + FileName + " to complete update.");
        }
    }

    static void addRecord() throws IOException{
        int rollNumber = readInteger("Enter Roll Number: ");
        if(rollExists(rollNumber)){
            System.out.println("Roll Number already exists!");
            return;
        }

        int age=readInteger("Enter Age: ");

        System.out.print("Enter Name: ");
        String name=keyboard.readLine().trim();
        if(name.isEmpty()){
            System.out.println("Invalid Name!");
            return;
        }
        double m1=readDouble("Enter sub1 marks :");
        double m2=readDouble("Enter sub2 marks :");
        double m3=readDouble("Enter sub3 marks :");
        double m4=readDouble("Enter sub4 marks :");
        double m5=readDouble("Enter sub5 marks :");

        String grade= calcGrade(m1,m2,m3,m4,m5);
        String record = createRecord(rollNumber,age,name,m1,m2,m3,m4,m5,grade);

        try (BufferedWriter bw=new BufferedWriter(new FileWriter(FileName,true))){
            bw.write(record);
            bw.newLine();
            System.out.println("Student Record has been successfully saved!");
        }
    }

    static void readAllRecords() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String line;
            while((line = br.readLine()) != null){
                String[] data=line.split("\\|");
                DisplayStudentDetails(data);
            }
        }
    }

    static void searchByRoll() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String line;
            int rollNumber=readInteger("Enter Roll Number to Search: ");
            while((line = br.readLine()) != null){
                String[] data=line.split("\\|");
                if(rollNumber == Integer.parseInt(data[0])){
                    DisplayStudentDetails(data);
                    return;
                }
            }
            System.out.println("Student Record Not Found!");
        }
    }

    static void searchByName() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String line;
            String name=keyboard.readLine().trim();
            while((line = br.readLine()) != null){
                String[] data=line.split("\\|");
                if(name.equalsIgnoreCase(data[2])){
                    DisplayStudentDetails(data);
                    return;
                }
            }
            System.out.println("Student Record Not Found!");
        }
    }

    static String createRecord(int rollNumber,int age,String name,double m1,double m2,double m3,double m4,double m5,String Grade){

        return rollNumber + "|" + age + "|" + name + "|" +
                m1 + "|" + m2 + "|" + m3 + "|" +
                m4 + "|" + m5 + "|" + Grade;
    }

    static String calcGrade(double m1,double m2,double m3,double m4,double m5){
        double avg=(m1+m2+m3+m4+m5)/5.0;
        if(avg <=100 && avg >91 ) return "S";
        else if(avg <=90 && avg >81 ) return "A";
        else if(avg <=80 && avg >71 ) return "B";
        else if(avg <=70 && avg >61 ) return "C";
        else if(avg <=60 && avg >51 ) return "D";
        else if(avg <=50 && avg >41 ) return "E";
        else return "F";
    }

    static double readDouble(String msg)throws IOException{
        while(true){
            try {
                System.out.println(msg);
                double marks=Double.parseDouble(keyboard.readLine().trim());
                if(marks >0 && marks <= 100)
                    return marks;
                System.out.println("Marks should be between 0 and 100 only!!!");
            }catch(NumberFormatException e){
                System.out.println("Enter Valid Marks!");
            }
        }
    }

    static int readInteger(String msg) throws IOException {
        while(true) {
            try{
                System.out.println(msg);
                return Integer.parseInt(keyboard.readLine());
            }catch(NumberFormatException e){
                System.out.println("Enter a valid roll no");
            }
        }
    }

    static boolean rollExists(int rollNumber) throws IOException {
        File file = new File(FileName);
        if(!file.exists()) return false;

        try(BufferedReader br= new BufferedReader(new FileReader(file))){
            String line;

            while((line = br.readLine()) != null){

                String[] data=line.split("\\|");
                if(rollNumber == Integer.parseInt(data[0])){
                    System.out.println("Roll Number already exists!");
                    return true;
                }
            }
        }
        return false;
    }

    static void DisplayStudentDetails(String[] data){
        System.out.println("RollNumber : "+data[0]);
        System.out.println("Name : "+data[1]);
        System.out.println("Age : "+data[2]);
        System.out.println("Grade :"+data[8]);
        System.out.println("Marks subject wise :");
        System.out.println("M1"+"\tM2"+"\tM3"+"\tM4"+"\tM5");
        System.out.println(data[3]+"\t"+data[4]+"\t"+data[5]+"\t"+data[6]+"\t"+data[7]);
        System.out.println();
    }
}