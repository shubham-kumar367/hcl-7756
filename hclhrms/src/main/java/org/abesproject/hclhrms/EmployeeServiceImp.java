
package org.abesproject.hclhrms;

import org.abesproject.hclhrms.entity.Employee;
import org.abesproject.hclhrms.repository.EmpRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImp {
    @Autowired
    private EmpRepoImp emprepo;

    public String addEmp() {
        Employee emp1 = new Employee(200L, "Rahul", "FSD", "Java", "Rahul123@gmail.com");
        return emprepo.addemp(emp1);
    }

    public List<Employee> getEmp() {
        return emprepo.getEmp();
    }

    public Employee getEmpId() {
        return emprepo.getEmpById(200L);
    }

    public List<Employee> addEmpbulk() {
        List<Employee> bulkEmp = List.of(
                new Employee(890L, "Ram", "Test Engineer", "Java full stack", "ram@gmail.com"),
                new Employee(891L, "Shubham", "Test Engineer", "Java full stack", "shhub@gmail.com"),
                new Employee(892L, "Sam", "Test Engineer", "Java full stack", "sam@gmail.com"),
                new Employee(893L, "Xam", "Test Engineer", "Java full stack", "xam@gmail.com")
        );

        return emprepo.addEmpbulk(bulkEmp);
    }

    public String deleteById() {
        return emprepo.deleteById(891L);
    }

    public String updateById() {
        Employee updatedEmp = new Employee(
                893L, "Xam", "Developer", "Spring Boot", "xam@gmail.com"
        );

        return emprepo.updateById(893L, updatedEmp);
    }
}
