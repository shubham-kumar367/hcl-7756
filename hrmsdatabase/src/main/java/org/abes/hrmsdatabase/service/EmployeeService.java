
package org.abes.hrmsdatabase.service;

import java.util.List;
import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createNewEmployee()
    {
        Employee emp1 = new Employee(200L, "Ram", "Java FSD", "Developer");
        employeeRepository.save(emp1);
        return "Employee added successfully";
    }

    public Employee getEmployeeById(long empid)
    {
        return employeeRepository.findById(empid).orElse(null);
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long empid, Employee employee)
    {
        Employee existing = employeeRepository.findById(empid).orElse(null);

        if (existing == null)
            return null;

        existing.setEmp_name(employee.getEmp_name());
        existing.setStack(employee.getStack());
        existing.setDesignation(employee.getDesignation());

        return employeeRepository.save(existing);
    }

    public String deleteEmployee(long empid)
    {
        if (!employeeRepository.existsById(empid))
            return "Employee not found";

        employeeRepository.deleteById(empid);
        return "Employee deleted successfully";
    }

    public boolean employeeExists(long empid)
    {
        return employeeRepository.existsById(empid);
    }

    public long getEmployeeCount()
    {
        return employeeRepository.count();
    }
}
