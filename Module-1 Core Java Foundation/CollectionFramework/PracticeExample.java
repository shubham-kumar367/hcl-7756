package CollectionFramework;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

public class PracticeExample {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        // CREATE
        employees.add(new Employee(103, "Neha", 45000));
        employees.add(new Employee(101, "Abhishek", 50000));
        employees.add(new Employee(102, "Shubham", 60000));

        // READ using Iterator
        System.out.println("Employees using Iterator:");
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // UPDATE
        for (Employee emp : employees) {
            if (emp.getId() == 102) {
                emp.setName("Shubham Kumar");
                emp.setSalary(65000);
            }
        }

        // DELETE using Iterator
        iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getId() == 103) {
                iterator.remove();
            }
        }

        // READ using ListIterator - forward
        System.out.println("\nEmployees using ListIterator forward:");
        ListIterator<Employee> listIterator = employees.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // READ using ListIterator - backward
        System.out.println("\nEmployees using ListIterator backward:");
        listIterator = employees.listIterator(employees.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // Increasing order by employee id
        System.out.println("\nEmployees in increasing order:");
        employees.sort(Comparator.comparingInt(Employee::getId));
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Decreasing order by employee id
        System.out.println("\nEmployees in decreasing order:");
        employees.sort(Comparator.comparingInt(Employee::getId).reversed());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
