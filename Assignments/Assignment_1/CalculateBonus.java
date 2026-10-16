import java.util.*;

public class CalculateBonus {

    static class Employee {
        int empId;
        String name;
        String role;
        double basic;
        double hra;
        double ta;
        double specialAllowance;
        double bonus;

        Employee(int empId, String name, String role, double basic, double hra, double ta, double specialAllowance) {
            this.empId = empId;
            this.name = name;
            this.role = role;
            this.basic = basic;
            this.hra = hra;
            this.ta = ta;
            this.specialAllowance = specialAllowance;
            this.bonus = calculateBonus();
        }

        double calculateBonus() {
            double bonusPercent;

            switch (role.toLowerCase()) {
                case "manager":
                    bonusPercent = 12;
                    break;
                case "admin":
                    bonusPercent = 10;
                    break;
                case "engineer":
                    bonusPercent = 12;
                    break;
                case "accountant":
                    bonusPercent = 8;
                    break;
                default:
                    bonusPercent = 0;
            }

            return (basic + hra) * bonusPercent / 100;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(100, "Rahul", "Manager", 40000, 15833, 3000, 5000));
        employees.add(new Employee(101, "Amit", "Admin", 35000, 15500, 2500, 4000));
        employees.add(new Employee(102, "Shubham", "Engineer", 38000, 17000, 3000, 4500));
        employees.add(new Employee(103, "Neha", "Accountant", 25000, 10000, 2000, 3000));

        employees.sort((e1, e2) -> Double.compare(e2.bonus, e1.bonus));

        for (Employee employee : employees) {
            System.out.println("---------------------------------------------");
            System.out.println("Emp_id: " + employee.empId);
            System.out.println("Name: " + employee.name);
            System.out.println("Bonus: Rs. " + Math.round(employee.bonus));
        }
        System.out.println("---------------------------------------------");
    }
}