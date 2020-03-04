package com.example.candidatedata.demo.controller;

import com.example.candidatedata.demo.model.Employee;
import com.example.candidatedata.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.awt.print.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //get
    @GetMapping("/recruiters")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //post
    @PostMapping("/recruiters")
    public Employee createEmployee(@Valid @RequestBody Employee recruiter){
        return employeeService.createEmployee(recruiter);
    }

//    put
    @PutMapping("/recruiters/{recruiterId}")
    public Optional<Employee> updateEmployee(@PathVariable Long employeeId,@Valid @RequestBody Employee employee){
        return employeeService.updateEmployee(employeeId,employee);
    }

    //delete
    @DeleteMapping("/recruiters/{recruiterId}")
    public Optional<?> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}
