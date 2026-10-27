
package org.abes.hrmsdatabase.controller;

import java.util.List;
import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String msg()
    {
        return "Welcome to Spring Boot App Development";
    }

    @GetMapping("/addemp")
    public String addNewEmployee()
    {
        return employeeService.createNewEmployee();
    }

    @GetMapping("/findbyid/{empid}")
    public Employee getEmpById(@PathVariable("empid") long empid)
    {
        return employeeService.getEmployeeById(empid);
    }

    @GetMapping("/allemployees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/addemployee")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/updateemployee/{empid}")
    public Employee updateEmployee(@PathVariable("empid") long empid, @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(empid, employee);
    }

    @DeleteMapping("/deleteemployee/{empid}")
    public String deleteEmployee(@PathVariable("empid") long empid)
    {
        return employeeService.deleteEmployee(empid);
    }

    @GetMapping("/employeeexists/{empid}")
    public boolean employeeExists(@PathVariable("empid") long empid)
    {
        return employeeService.employeeExists(empid);
    }

    @GetMapping("/employeecount")
    public long getEmployeeCount()
    {
        return employeeService.getEmployeeCount();
    }
}
