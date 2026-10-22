package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;

import java.util.List;

public interface EmployeeRepo {
    String addemp(Employee emp);
    List<Employee> addEmpbulk(List<Employee> bulkEmp);
    List<Employee> getEmp();
    Employee getEmpById(long empid);
    String deleteById(long empid);
    String updateById(long emp, Employee updatedEmp);
;}
