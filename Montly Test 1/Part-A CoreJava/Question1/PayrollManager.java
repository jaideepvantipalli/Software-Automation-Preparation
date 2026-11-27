import java.util.*;
import java.util.stream.Collectors;

abstract class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public String getName(){
        return name;
    }
    public String getDepartment(){
        return department;
    }
    abstract double CalculateSalary();
}
class FullTimeEmployee extends Employee {
    double salary;
    double bonus;
    public FullTimeEmployee(int id, String name, String department, double salary, double bonus) {
        super(id, name, department);
        this.salary = salary;
        this.bonus = bonus;
    }
    @Override
    public double CalculateSalary(){
        return salary+bonus;
    }
}
class ContractEmployee extends Employee {
    double hoursRate;
    int hoursWorked;
    public ContractEmployee(int id, String name, String department, double hoursRate, int hoursWorked) {
        super(id,name,department);
        this.hoursRate = hoursRate;
        this.hoursWorked = hoursWorked;
    }
    @Override
    public double CalculateSalary(){
        return hoursRate *  hoursWorked;
    }
}

public class PayrollManager{

    public static Map<String,Double> getDepartmentWiseTotalSalary(List<Employee> employees){
        Map<String, Double> getTotalSalary =
                employees.stream().collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(Employee::CalculateSalary)
                        )
                );
        return getTotalSalary;
    }
    public static List<String> getTopEarners(List<Employee> employees , double threshold){
        List<String> leaderBoardEmployeees =
                employees.stream()
                        .filter(employee -> employee.CalculateSalary() > threshold)
                        .map(Employee::getName)
                        .toList();
        return leaderBoardEmployeees;
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee(101, "Alice", "IT", 60000, 10000));
        employees.add(new ContractEmployee(102, "Bob", "IT", 500, 100));
        employees.add(new FullTimeEmployee(103, "Charlie", "HR", 55000, 5000));

        System.out.println("Department Wise Total Salary : ");

        Map<String, Double> getTotalSalary = getDepartmentWiseTotalSalary(employees);
        getTotalSalary.forEach((department,totalSalary) ->{
            System.out.println(department+" : "+ totalSalary);
        });

        System.out.println("Enter the threshold to retrive employees");
        Double threshold = sc.nextDouble();

        List<String> leaderboardEmployees = getTopEarners(employees,threshold);

        if(leaderboardEmployees.size() > 0) {
            System.out.println("The top employees are:");
            for(String employee : leaderboardEmployees){
                System.out.println(employee);
            }
        }else System.out.println("No Employees foun with given threshold");
        sc.close();
    }
}

