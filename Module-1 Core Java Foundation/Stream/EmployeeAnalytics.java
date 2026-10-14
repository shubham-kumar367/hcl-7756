package Stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class EmployeeAnalytics {
    record Employee(int id, String name, String dept, BigDecimal salary,
                    LocalDate hireDate, Set<String> skills) {}

    static Employee e(int id, String n, String d, int sal, String date, String... skills) {
        return new Employee(id, n, d, BigDecimal.valueOf(sal),
                LocalDate.parse(date), Set.of(skills));
    }

    public static void main(String[] args) {
        List<Employee> emps = List.of(
                e(1, "Asha",   "Engineering",  90000, "2019-03-01", "Java", "SQL"),
                e(2, "Ravi",   "Engineering", 120000, "2016-07-15", "Java", "Spring", "AWS"),
                e(3, "Meera",  "HR",           60000, "2020-01-10", "Recruiting"),
                e(4, "John",   "Sales",        70000, "2018-11-20", "Negotiation", "CRM"),
                e(5, "Priya",  "Engineering", 105000, "2021-05-05", "Java", "React"),
                e(6, "Karan",  "Sales",        65000, "2022-02-14", "CRM"),
                e(7, "Sara",   "HR",           62000, "2017-09-09", "Recruiting", "Payroll"),
                e(8, "Vikram", "Finance",      80000, "2015-04-30", "Excel", "SQL"));

        System.out.println("=== LEVEL 1: BASICS ===");
        // Q1: Names of Engineering employees, sorted by salary descending
        List<String> q1 = emps.stream()
                .filter(emp -> "Engineering".equals(emp.dept()))
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .map(Employee::name)
                .toList();
        System.out.println("Q1: " + q1);

        // Q2: Names of the top 3 earners overall
        List<String> q2 = emps.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .limit(3)
                .map(Employee::name)
                .toList();
        System.out.println("Q2: " + q2);

        // Q3: Employees hired before 2018-01-01, oldest hire first
        List<String> q3 = emps.stream()
                .filter(emp -> emp.hireDate().isBefore(LocalDate.parse("2018-01-01")))
                .sorted(Comparator.comparing(Employee::hireDate))
                .map(emp -> String.format("%s (%d)", emp.name(), emp.hireDate().getYear()))
                .toList();
        System.out.println("Q3: " + q3);

        // Q4: All names joined as a comma-separated string
        String q4 = emps.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", "));
        System.out.println("Q4: " + q4);


        System.out.println("\n=== LEVEL 2: REDUCTION AND MATCHING ===");
        // Q5: Total payroll using reduce
        BigDecimal q5 = emps.stream()
                .map(Employee::salary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Q5: " + q5);

        // Q6: Matching checks
        boolean anyAws = emps.stream().anyMatch(emp -> emp.skills().contains("AWS"));
        boolean allAbove50k = emps.stream().allMatch(emp -> emp.salary().compareTo(BigDecimal.valueOf(50000)) > 0);
        boolean noneAbove150k = emps.stream().noneMatch(emp -> emp.salary().compareTo(BigDecimal.valueOf(150000)) > 0);
        System.out.printf("Q6: anyMatch AWS? %b | allMatch >50k? %b | noneMatch >150k? %b%n", anyAws, allAbove50k, noneAbove150k);

        // Q7: Highest-paid employee name or "none"
        String q7 = emps.stream()
                .max(Comparator.comparing(Employee::salary))
                .map(Employee::name)
                .orElse("none");
        System.out.println("Q7: " + q7);


        System.out.println("\n=== LEVEL 3: GROUPING AND COLLECTING ===");
        // Q8: Average salary per department
        Map<String, Double> q8 = emps.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingDouble(emp -> emp.salary().doubleValue())
                ));
        System.out.println("Q8: " + q8);

        // Q9: Highest-paid employee name per department
        Map<String, String> q9 = emps.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee::salary)),
                                optEmp -> optEmp.map(Employee::name).orElse("none")
                        )
                ));
        System.out.println("Q9: " + q9);

        // Q10: Partition by salary >= 75000
        Map<Boolean, List<String>> q10 = emps.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.salary().compareTo(BigDecimal.valueOf(75000)) >= 0,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));
        System.out.println("Q10: " + q10);


        System.out.println("\n=== LEVEL 4: NESTED DATA (flatMap) ===");
        // Q11: Sorted list of distinct skills
        List<String> q11 = emps.stream()
                .flatMap(emp -> emp.skills().stream())
                .distinct()
                .sorted()
                .toList();
        System.out.println("Q11: " + q11);

        // Q12: Skill frequency map and the most common skill
        Map<String, Long> skillCounts = emps.stream()
                .flatMap(emp -> emp.skills().stream())
                .collect(Collectors.groupingBy(skill -> skill, Collectors.counting()));

        Map.Entry<String, Long> q12 = skillCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();
        System.out.printf("Q12: Frequency Map: %s%n     Most Common Skill: %s = %d%n", skillCounts, q12.getKey(), q12.getValue());


        System.out.println("\n=== REQUIRED EXTRAS ===");
        // E1: toMap with duplicate check and merge fix
        try {
            // Intentionally triggering duplicate key error via map collision simulation
            Stream.of(e(1, "Asha", "Eng", 90000, "2019-03-01"), e(1, "Duplicate-Asha", "Eng", 95000, "2019-03-01"))
                    .collect(Collectors.toMap(Employee::id, Employee::name));
        } catch (IllegalStateException ex) {
            System.out.println("E1 (Caught Expected Exception): " + ex);
        }

        // Fixing it with a merge function
        Map<Integer, String> fixedMap = Stream.of(e(1, "Asha", "Eng", 90000, "2019-03-01"), e(1, "Duplicate-Asha", "Eng", 95000, "2019-03-01"))
                .collect(Collectors.toMap(
                        Employee::id,
                        Employee::name,
                        (existing, replacement) -> existing // Keep the original mapping
                ));
        System.out.println("E1 Fixed Map: " + fixedMap);

        // E3: Lazy evaluation test
        System.out.print("E3: Executing stream with no terminal operation... Result: ");
        Stream<String> lazyStream = emps.stream().map(emp -> {
            System.out.println("This will not print!");
            return emp.name();
        });
        System.out.println(lazyStream); // Only prints the reference string object, no names map.
    }
}

