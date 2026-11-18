import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentManagement {
    public static void main(String[] args) throws IOException {
        StudentService service = new StudentService();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

                int choice;
                try{
                    choice = Integer.parseInt(br.readLine());
                }catch(NumberFormatException e){
                    System.out.println("Invalid Input");
                    continue;
                }

                switch (choice) {

                    case 1:
                        service.addRecord();
                        break;

                    case 2:
                        service.readAllRecords();
                        break;

                    case 3:
                        service.searchByRollNumber();
                        break;

                    case 4:
                        service.searchByName();
                        break;

                    case 5:
                        service.deleteRecord();
                        break;

                    case 6:
                        service.updateRecord();
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
}
