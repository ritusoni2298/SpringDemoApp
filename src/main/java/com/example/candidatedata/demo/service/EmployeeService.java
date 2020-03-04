package com.example.candidatedata.demo.service;

import com.example.candidatedata.demo.model.Employee;
import com.example.candidatedata.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employee){
        return employeeRepository.findById(employeeId).map(recruiterr-> {
            recruiterr.setName(employee.getName());
//            recruiterr.setLastName(recruiter.getLastName());
            recruiterr.setEmail(employee.getEmail());
//            recruiterr.setPassword(employee.getPassword());
            recruiterr.setRoles(employee.getRoles());
            return employeeRepository.save(recruiterr);
        });
    }

    public Optional<?> deleteEmployee(@PathVariable Long employeeId){
        return employeeRepository.findById(employeeId).map(
                recruiter1-> {
                    employeeRepository.delete(recruiter1);
                    return ResponseEntity.ok().build();
                }
        );
    }

}
