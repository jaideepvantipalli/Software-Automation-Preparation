
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class Employee {

    private int id;
    private String name;
    private String department;
    private long salary;

    public Employee(int id, String name,
                    String department, long salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class CollectorsClassDemo {

    public static void main(String[] args) {

        List<Employee> employees = List.of(

                new Employee(101, "Jaideep", "IT", 60000),
                new Employee(102, "Rahul", "HR", 40000),
                new Employee(103, "Priya", "IT", 75000),
                new Employee(104, "Arjun", "Finance", 50000),
                new Employee(105, "Sneha", "HR", 55000),
                new Employee(106, "Kiran", "Finance", 80000),
                new Employee(107, "Ravi", "IT", 45000),
                new Employee(108, "Anjali", "Sales", 65000)

        );


        // ==========================================
        // 1. GROUPING BY()
        // ==========================================

        System.out.println("\n===== GROUPING BY =====");

        // Variant 1: groupingBy(classifier)

        Map<String, List<Employee>> group1 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment
                        ));

        System.out.println("\nGrouping by department:");
        group1.forEach((dept, list) ->
                System.out.println(dept + " : " + list)
        );


        // Variant 2: groupingBy(classifier, downstream)

        Map<String, Long> group2 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        ));

        System.out.println("\nEmployee count by department:");
        System.out.println(group2);


        // Variant 3: groupingBy(classifier, mapFactory, downstream)

        Map<String, List<Employee>> group3 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                TreeMap::new,
                                Collectors.toList()
                        ));

        System.out.println("\nGrouping using TreeMap:");
        System.out.println(group3);


        // ==========================================
        // 2. GROUPING BY CONCURRENT()
        // ==========================================

        System.out.println("\n===== GROUPING BY CONCURRENT =====");

        // Variant 1: groupingByConcurrent(classifier)

        ConcurrentMap<String, List<Employee>> concurrent1 =
                employees.parallelStream()
                        .collect(Collectors.groupingByConcurrent(
                                Employee::getDepartment
                        ));

        System.out.println("\nConcurrent grouping:");
        System.out.println(concurrent1);


        // Variant 2: groupingByConcurrent(classifier, downstream)

        ConcurrentMap<String, Long> concurrent2 =
                employees.parallelStream()
                        .collect(Collectors.groupingByConcurrent(
                                Employee::getDepartment,
                                Collectors.counting()
                        ));

        System.out.println("\nConcurrent employee count:");
        System.out.println(concurrent2);


        // Variant 3: groupingByConcurrent(classifier, mapFactory, downstream)

        ConcurrentMap<String, List<Employee>> concurrent3 =
                employees.parallelStream()
                        .collect(Collectors.groupingByConcurrent(
                                Employee::getDepartment,
                                ConcurrentHashMap::new,
                                Collectors.toList()
                        ));

        System.out.println("\nConcurrent grouping with map factory:");
        System.out.println(concurrent3);


        // ==========================================
        // 3. PARTITIONING BY()
        // ==========================================

        System.out.println("\n===== PARTITIONING BY =====");

        // Variant 1: partitioningBy(predicate)

        Map<Boolean, List<Employee>> partition1 =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getSalary() >= 50000
                        ));

        System.out.println("\nEmployees by salary condition:");
        System.out.println(partition1);


        // Variant 2: partitioningBy(predicate, downstream)

        Map<Boolean, Long> partition2 =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getSalary() >= 50000,
                                Collectors.counting()
                        ));

        System.out.println("\nCount of employees in each partition:");
        System.out.println(partition2);


        // ==========================================
        // 4. JOINING()
        // ==========================================

        System.out.println("\n===== JOINING =====");

        List<String> names = employees.stream()
                .map(Employee::getName)
                .toList();


        // Variant 1: joining()

        String joining1 = names.stream()
                .collect(Collectors.joining());

        System.out.println("\nJoining without delimiter:");
        System.out.println(joining1);


        // Variant 2: joining(delimiter)

        String joining2 = names.stream()
                .collect(Collectors.joining(", "));

        System.out.println("\nJoining with delimiter:");
        System.out.println(joining2);


        // Variant 3: joining(delimiter, prefix, suffix)

        String joining3 = names.stream()
                .collect(Collectors.joining(
                        ", ",
                        "[",
                        "]"
                ));

        System.out.println("\nJoining with delimiter, prefix and suffix:");
        System.out.println(joining3);


        // ==========================================
        // 5. SUMMING INT, LONG, DOUBLE
        // ==========================================

        System.out.println("\n===== SUMMING =====");


        // summingInt()

        Integer sumInt = employees.stream()
                .collect(Collectors.summingInt(
                        e -> (int) e.getSalary()
                ));

        System.out.println("\nSumming Int:");
        System.out.println(sumInt);


        // summingLong()

        Long sumLong = employees.stream()
                .collect(Collectors.summingLong(
                        Employee::getSalary
                ));

        System.out.println("\nSumming Long:");
        System.out.println(sumLong);


        // summingDouble()

        Double sumDouble = employees.stream()
                .collect(Collectors.summingDouble(
                        e -> (double) e.getSalary()
                ));

        System.out.println("\nSumming Double:");
        System.out.println(sumDouble);


        // ==========================================
        // 6. AVERAGING INT, LONG, DOUBLE
        // ==========================================

        System.out.println("\n===== AVERAGING =====");


        // averagingInt()

        Double avgInt = employees.stream()
                .collect(Collectors.averagingInt(
                        e -> (int) e.getSalary()
                ));

        System.out.println("\nAveraging Int:");
        System.out.println(avgInt);


        // averagingLong()

        Double avgLong = employees.stream()
                .collect(Collectors.averagingLong(
                        Employee::getSalary
                ));

        System.out.println("\nAveraging Long:");
        System.out.println(avgLong);


        // averagingDouble()

        Double avgDouble = employees.stream()
                .collect(Collectors.averagingDouble(
                        e -> (double) e.getSalary()
                ));

        System.out.println("\nAveraging Double:");
        System.out.println(avgDouble);


        // ==========================================
        // 7. SUMMARIZING INT, LONG, DOUBLE
        // ==========================================

        System.out.println("\n===== SUMMARIZING =====");


        // summarizingInt()

        IntSummaryStatistics statsInt =
                employees.stream()
                        .collect(Collectors.summarizingInt(
                                e -> (int) e.getSalary()
                        ));

        System.out.println("\nInt Summary Statistics:");
        System.out.println("Count: " + statsInt.getCount());
        System.out.println("Sum: " + statsInt.getSum());
        System.out.println("Min: " + statsInt.getMin());
        System.out.println("Max: " + statsInt.getMax());
        System.out.println("Average: " + statsInt.getAverage());


        // summarizingLong()

        LongSummaryStatistics statsLong =
                employees.stream()
                        .collect(Collectors.summarizingLong(
                                Employee::getSalary
                        ));

        System.out.println("\nLong Summary Statistics:");
        System.out.println("Count: " + statsLong.getCount());
        System.out.println("Sum: " + statsLong.getSum());
        System.out.println("Min: " + statsLong.getMin());
        System.out.println("Max: " + statsLong.getMax());
        System.out.println("Average: " + statsLong.getAverage());


        // summarizingDouble()

        DoubleSummaryStatistics statsDouble =
                employees.stream()
                        .collect(Collectors.summarizingDouble(
                                e -> (double) e.getSalary()
                        ));

        System.out.println("\nDouble Summary Statistics:");
        System.out.println("Count: " + statsDouble.getCount());
        System.out.println("Sum: " + statsDouble.getSum());
        System.out.println("Min: " + statsDouble.getMin());
        System.out.println("Max: " + statsDouble.getMax());
        System.out.println("Average: " + statsDouble.getAverage());


        // ==========================================
        // 8. MIN BY()
        // ==========================================

        System.out.println("\n===== MIN BY =====");

        // Find employee with minimum salary

        Optional<Employee> minimum =
                employees.stream()
                        .collect(Collectors.minBy(
                                Comparator.comparingLong(Employee::getSalary)
                        ));

        System.out.println("\nMinimum salary employee:");
        minimum.ifPresent(System.out::println);


        // ==========================================
        // 9. MAX BY()
        // ==========================================

        System.out.println("\n===== MAX BY =====");

        // Find employee with maximum salary

        Optional<Employee> maximum =
                employees.stream()
                        .collect(Collectors.maxBy(
                                Comparator.comparingLong(Employee::getSalary)
                        ));

        System.out.println("\nMaximum salary employee:");
        maximum.ifPresent(System.out::println);


        // ==========================================
        // 10. MIN BY WITH GROUPING BY
        // ==========================================

        System.out.println("\n===== MIN BY WITH GROUPING =====");

        Map<String, Optional<Employee>> minimumByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.minBy(
                                        Comparator.comparingLong(Employee::getSalary)
                                )
                        ));

        minimumByDept.forEach((dept, emp) ->
                System.out.println(dept + " : " + emp)
        );


        // ==========================================
        // 11. MAX BY WITH GROUPING BY
        // ==========================================

        System.out.println("\n===== MAX BY WITH GROUPING =====");

        Map<String, Optional<Employee>> maximumByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy(
                                        Comparator.comparingLong(Employee::getSalary)
                                )
                        ));

        maximumByDept.forEach((dept, emp) ->
                System.out.println(dept + " : " + emp)
        );


        // ==========================================
        // 12. SUMMING WITH GROUPING BY
        // ==========================================

        System.out.println("\n===== SUMMING BY DEPARTMENT =====");

        Map<String, Long> salaryByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingLong(Employee::getSalary)
                        ));

        System.out.println(salaryByDept);


        // ==========================================
        // 13. SUMMARIZING WITH GROUPING BY
        // ==========================================

        System.out.println("\n===== SUMMARY BY DEPARTMENT =====");

        Map<String, LongSummaryStatistics> summaryByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summarizingLong(Employee::getSalary)
                        ));

        summaryByDept.forEach((dept, stats) ->
                System.out.println(dept + " : " + stats)
        );


        // ==========================================
        // 14. AVERAGING WITH GROUPING BY
        // ==========================================

        System.out.println("\n===== AVERAGE BY DEPARTMENT =====");

        Map<String, Double> averageByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingLong(Employee::getSalary)
                        ));

        System.out.println(averageByDept);


        System.out.println("\n===== COLLECTORS DEMO COMPLETED =====");
    }
}
