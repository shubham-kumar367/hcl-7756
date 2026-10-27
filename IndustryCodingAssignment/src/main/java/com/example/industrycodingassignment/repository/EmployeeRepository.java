
package com.example.industrycodingassignment.repository;

import com.example.industrycodingassignment.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository
{
    private final Map<String, Employee> employees = new LinkedHashMap<>();

    public Employee save(Employee employee)
    {
        employees.put(employee.getEmpId(), employee);
        return employee;
    }

    public List<Employee> findAll()
    {
        return new ArrayList<>(employees.values());
    }

    public Employee findById(String empId)
    {
        return employees.get(empId);
    }

    public boolean existsById(String empId)
    {
        return employees.containsKey(empId);
    }

    public void deleteById(String empId)
    {
        employees.remove(empId);
    }
}
