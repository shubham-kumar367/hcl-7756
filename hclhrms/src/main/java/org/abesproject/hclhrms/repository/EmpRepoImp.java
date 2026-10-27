
package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmpRepoImp implements EmployeeRepo {
    List<Employee> emplist = new ArrayList<>();

    @Override
    public String addemp(Employee emp) {
        emplist.add(emp);
        return "Employee Added Successfully";
    }

    @Override
    public List<Employee> addEmpbulk(List<Employee> bulkEmp) {
        emplist.addAll(bulkEmp);
        return emplist;
    }

    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpById(long empid) {
        return emplist.stream()
                .filter(e -> e.getEmp_id() == empid)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String deleteById(long empid) {
        boolean status = emplist.removeIf(e -> e.getEmp_id() == empid);

        if (status) {
            return "Employee deleted successfully";
        }

        return "Employee is not registered with id " + empid;
    }


    @Override
    public String updateById(long empid, Employee updatedEmp) {
        Employee existingEmp = getEmpById(empid);

        if (existingEmp == null) {
            return "Employee not found";
        }

        existingEmp.setEmp_name(updatedEmp.getEmp_name());
        existingEmp.setDesignation(updatedEmp.getDesignation());
        existingEmp.setProgStack(updatedEmp.getProgStack());
        existingEmp.setEmail(updatedEmp.getEmail());

        return "Employee updated successfully";
    }

}
