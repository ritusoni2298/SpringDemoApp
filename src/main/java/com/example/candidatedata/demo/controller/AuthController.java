package com.example.candidatedata.demo.controller;

import com.example.candidatedata.demo.model.Employee;
import com.example.candidatedata.demo.model.Role;
import com.example.candidatedata.demo.model.RoleName;
import com.example.candidatedata.demo.payloads.ApiResponse;
import com.example.candidatedata.demo.payloads.JwtAuthenticationResponse;
import com.example.candidatedata.demo.payloads.LoginRequest;
import com.example.candidatedata.demo.payloads.Signup;
import com.example.candidatedata.demo.repository.EmployeeRepository;
import com.example.candidatedata.demo.repository.RoleRepository;
import com.example.candidatedata.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        System.out.print("hello");
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Signup signup) throws Exception {
        if(employeeRepository.existsByEmail(signup.getEmail())){
            return new ResponseEntity(new ApiResponse(false,"email already exists"), HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee(signup.getName(),signup.getEmail(),signup.getPassword());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        Role emprole = new Role(RoleName.ROLE_ADMIN);
        employee.setRoles(Collections.singleton(emprole));
        Employee result = employeeRepository.save(employee);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getEmail().substring(0,5)).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }


}
