import java.util.*;
import java.util.stream.Collectors;

public class CaseStudy1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> list = new ArrayList<>(Arrays.asList(
                new Employee("Jaideep", 21, "IT"),
                new Employee("Rahul", 25, "HR"),
                new Employee("Priya", 23, "IT"),
                new Employee("Kiran", 30, "Finance"),
                new Employee("Anusha", 28, "HR"),
                new Employee("Suresh", 35, "Finance"),
                new Employee("Ravi", 22, "IT"),
                new Employee("Sneha", 26, "Marketing"),
                new Employee("Arjun", 40, "Finance")
        ));
        while (true) {
            System.out.println("\n========= EMPLOYEE MANAGEMENT SYSTEM =========");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Group Employees by Department");
            System.out.println("4. Group Employees by Exact Age");
            System.out.println("5. Group Employees by Age Ranges");
            System.out.println("6. Find Eldest & Youngest Employee");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                continue;
            }
            System.out.println();
            switch (choice) {
                case 1:
                    addEmployee(scanner, list);
                    break;
                case 2:
                    viewAllEmployees(list);
                    break;
                case 3:
                    groupByDepartment(list);
                    break;
                case 4:
                    groupByExactAge(list);
                    break;
                case 5:
                    groupByAgeRange(list);
                    break;
                case 6:
                    findEldestAndYoungest(list);
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please select between 1 and 7.");
            }
        }
    }

    private static void addEmployee(Scanner scanner, List<Employee> list) {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine().trim();

        int age = 0;
        while (true) {
            System.out.print("Enter Employee Age: ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age > 0) break;
                System.out.println("Age must be a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid age! Please enter a valid integer.");
            }
        }
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();
        list.add(new Employee(name, age, dept));
        System.out.println("Employee '" + name + "' added successfully!");
    }

    private static void viewAllEmployees(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("--- All Employees ---");
        list.forEach(System.out::println);
    }

    private static void groupByDepartment(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees to group.");
            return;
        }
        Map<String, List<Employee>> map = list.stream().collect(Collectors.groupingBy(Employee::getDept));

        map.forEach((dept, employees) -> {
            System.out.println("Department: " + dept);
            employees.stream().map(Employee::getName).forEach(name -> System.out.println("  - " + name));
        });
    }

    private static void groupByExactAge(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees to group.");
            return;
        }
        Map<Integer, List<Employee>> ageGroups = list.stream()
                .collect(Collectors.groupingBy(Employee::getAge, TreeMap::new, Collectors.toList()));

        ageGroups.forEach((age, employees) -> System.out.println("Age " + age + " : " + employees));
    }

    private static void groupByAgeRange(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees to group.");
            return;
        }
        Map<String, List<Employee>> ageRange = list.stream()
                .collect(Collectors.groupingBy(employee -> {
                            int age = employee.getAge();
                            if (age < 20) return "Under 20";
                            else if (age <= 25) return "20-25";
                            else if (age <= 30) return "26-30";
                            else if (age <= 35) return "31-35";
                            else return "36+";
                        },
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        ageRange.forEach((range, employees) -> {
            System.out.println("Age Range [" + range + "]:");
            employees.stream().map(Employee::getName).forEach(name -> System.out.println("  - " + name));
        });
    }

    private static void findEldestAndYoungest(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No employees in the list.");
            return;
        }
        Optional<Employee> oldest = list.stream().max(Comparator.comparingInt(Employee::getAge));
        Optional<Employee> youngest = list.stream().min(Comparator.comparingInt(Employee::getAge));

        oldest.ifPresent(emp -> System.out.println("Eldest Employee  : " + emp));
        youngest.ifPresent(emp -> System.out.println("Youngest Employee: " + emp));
    }
}

class Employee {
    private String name, dept;
    private int age;
    public Employee(String name, int age, String dept) {
        this.name = name;
        this.age = age;
        this.dept = dept;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDept() {
        return dept;
    }
    @Override
    public String toString() {
        return name + " | " + age + " | " + dept;
    }
}