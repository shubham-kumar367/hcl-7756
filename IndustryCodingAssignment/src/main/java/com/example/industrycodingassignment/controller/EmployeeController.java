
package com.example.industrycodingassignment.controller;

import com.example.industrycodingassignment.model.Employee;
import com.example.industrycodingassignment.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController
{
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee)
    {
        Employee created = employeeService.create(employee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getEmpId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll()
    {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable String id)
    {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(
            @PathVariable String id,
            @Valid @RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patch(
            @PathVariable String id,
            @RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.patch(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
