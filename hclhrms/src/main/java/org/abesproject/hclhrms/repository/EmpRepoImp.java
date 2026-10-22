package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpRepoImp implements EmployeeRepo{

    List<Employee> emplist = new ArrayList<>();

    @Override
    public String addemp(Employee emp) {
        emplist.add(emp);
        return "Employee Added Successfully";
    }

    @Override
    public List<Employee> addEmpbulk(List<Employee> bulkEmp) {
        List<Employee> emplist = List.of(
                new Employee(890L, "Ram", "Test Engineer", "Java full stack", "ram@gmail.com"),
                new Employee(891L, "Shubham", "Test Engineer", "Java full stack", "shhub@gmail.com"),
                new Employee(892L, "Sam", "Test Engineer", "Java full stack", "sam@gmail.com"),
                new Employee(893L, "Xam", "Test Engineer", "Java full stack", "xam@gmail.com")
        );
        return emplist;
    }

    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpById(long empid) {
        return emplist.stream().filter(e -> e.getEmp_id() == empid).findFirst().orElse(null);
    }

    @Override
    public String deleteById(long empid) {
        return "";
    }

    @Override
    public String updateById(long emp, Employee updatedEmp) {
        return "";
    }
}
