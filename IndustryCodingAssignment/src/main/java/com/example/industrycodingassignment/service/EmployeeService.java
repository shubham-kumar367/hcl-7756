
package com.example.industrycodingassignment.service;

import com.example.industrycodingassignment.model.Employee;
import com.example.industrycodingassignment.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee)
    {
        if(employee.getEmpId() == null || employee.getEmpId().isBlank())
        {
            throw new IllegalArgumentException("Employee ID is required");
        }

        validateRequiredFields(employee);

        if(employeeRepository.existsById(employee.getEmpId()))
        {
            throw new IllegalStateException("Employee ID already exists");
        }

        if(employee.getActive() == null)
        {
            employee.setActive(true);
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> findAll()
    {
        return employeeRepository.findAll();
    }

    public Employee findById(String empId)
    {
        Employee employee = employeeRepository.findById(empId);

        if(employee == null)
        {
            throw new IllegalArgumentException("Employee not found: " + empId);
        }

        return employee;
    }

    public Employee update(String empId, Employee updatedEmployee)
    {
        Employee employee = findById(empId);

        validateRequiredFields(updatedEmployee);

        employee.setName(updatedEmployee.getName());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setCity(updatedEmployee.getCity());
        employee.setActive(updatedEmployee.getActive() == null
                ? true : updatedEmployee.getActive());

        return employeeRepository.save(employee);
    }

    public Employee patch(String empId, Employee updates)
    {
        Employee employee = findById(empId);

        if(updates.getName() != null)
        {
            validateField(updates.getName(), "Name");
            employee.setName(updates.getName());
        }

        if(updates.getDepartment() != null)
        {
            validateField(updates.getDepartment(), "Department");
            employee.setDepartment(updates.getDepartment());
        }

        if(updates.getCity() != null)
        {
            validateField(updates.getCity(), "City");
            employee.setCity(updates.getCity());
        }

        if(updates.getActive() != null)
        {
            employee.setActive(updates.getActive());
        }

        return employeeRepository.save(employee);
    }

    public void delete(String empId)
    {
        findById(empId);
        employeeRepository.deleteById(empId);
    }

    private void validateRequiredFields(Employee employee)
    {
        validateField(employee.getName(), "Name");
        validateField(employee.getDepartment(), "Department");
        validateField(employee.getCity(), "City");
    }

    private void validateField(String value, String field)
    {
        if(value == null || value.isBlank())
        {
            throw new IllegalArgumentException(field + " is required");
        }
    }
}
