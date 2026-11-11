import java.util.*;
import java.util.stream.Collectors;

public class EmployeesCaseStudy {
    public static void main(String[] args) {
        List<Employee> list=Arrays.asList(
                new Employee("Jaideep", 21, "IT"),
                new Employee("Rahul", 25, "HR"),
                new Employee("Priya", 23, "IT"),
                new Employee("Kiran", 30, "Finance"),
                new Employee("Anusha", 28, "HR"),
                new Employee("Suresh", 35, "Finance"),
                new Employee("Ravi", 22, "IT"),
                new Employee("Sneha", 26, "Marketing"),
                new Employee("Arjun", 40, "Finance")
        );
        //group employee by using department name with the help pf streams
        Map<String,List<Employee>> map = list.stream().collect(Collectors.groupingBy(Employee::getDept));
        map.forEach((dept,employee)->{
            System.out.println("Department : "+dept);
            employee.stream().map(Employee::getName).forEach(System.out::println);
        });
        System.out.println();

        //group employee based on age
        Map<Integer,List<Employee>> ageGroups=list.stream().collect(
                Collectors.groupingBy(Employee::getAge)
        );
        ageGroups.forEach((age,Employee)->{
            System.out.println(age+" : "+Employee);
        });

        //group employee based on age ranges(50-60,61-70...)
        Map<String,List<Employee>> ageRange = list.stream()
                .collect(
                Collectors.groupingBy(employee ->
                {
                    int age=employee.getAge();
                    if( age >=20 && age <=25) return "20-25";
                    else if (age >=26 && age <=30) return "26-30";
                    else if (age >=31 && age <=35) return "31-35";
                    else return "36+";
                },
                        LinkedHashMap::new,
                        Collectors.toList())
        );

        ageRange.forEach( (range,employee) -> {
            System.out.println("\nAge Range : "+range);
            employee.stream().map(Employee::getName).forEach(System.out::println);
        });

        //find eldest employee
        Optional<Employee> oldest=list.stream().max(Comparator.comparingInt(Employee::getAge));
        oldest.ifPresent(System.out::println);
        //find youngest employee

        Optional<Employee> youngest=list.stream().min(Comparator.comparingInt(Employee::getAge));
        youngest.ifPresent(System.out::println);
    }
}
class Employee{
    private String name,dept;
    private int age;
    public Employee(String name,int age,String dept){
        this.name=name;
        this.age=age;
        this.dept=dept;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getDept(){
        return dept;
    }
    @Override
    public String toString(){
        return name + " | "+age+" | "+dept;
    }

}
