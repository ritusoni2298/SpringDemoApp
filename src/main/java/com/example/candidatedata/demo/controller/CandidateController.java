package com.example.candidatedata.demo.controller;

import com.example.candidatedata.demo.model.Candidate;
import com.example.candidatedata.demo.repository.CandidateRepository;
import com.example.candidatedata.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CandidateController {
    @Autowired
    CandidateRepository candidateRepository;

//    @Autowired
//    RecruiterRepository recruiterRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    //get
    @GetMapping("/recruiters/{recruiterId}/candidates")
    public Page<Candidate> getAllCandidatesByEmployeeId(@PathVariable(value="recruiterId") Long employeeId, Pageable pageable){
        return candidateRepository.findByEmployeeId(employeeId, pageable);
    }

    //post
    @PostMapping("/recruiters/{recruiterId}/candidates")
    public Optional<Candidate> addCandidate(@PathVariable (value = "recruiterId") Long employeeId, @Valid @RequestBody Candidate candidate){
        return employeeRepository.findById(employeeId).map(post -> {
            candidate.setEmployee(post);
            return candidateRepository.save(candidate);
        });
    }

    //put
    @PutMapping("/recruiters/{recruiterId}/candidates/{candidateId}")
    public Optional<Candidate> updateCandidateDetails(@PathVariable (value="recruiterId") Long recruiterId,
                                            @PathVariable (value="candidateId") Long candidateId,
                                            @Valid @RequestBody Candidate candidateRequest){
        return candidateRepository.findById(candidateId).map(
                candidate->{
                    candidate.setEmail(candidateRequest.getEmail());
                    candidate.setFirstName(candidateRequest.getFirstName());
                    candidate.setLastName(candidateRequest.getLastName());
                    candidate.setPhoneno(candidateRequest.getPhoneno());
                    candidate.setReference(candidateRequest.getReference());
                    return candidateRepository.save(candidate);
                }
        );
    }

    //delete
    //provide exceptional handling of all the controllers
    @DeleteMapping("/recruiters/{recruiterId}/candidates/{candidateId}")
    public Optional<?> deleteCandidate(@PathVariable(value = "recruiterId") Long employeeId,
                                                @PathVariable(value = "candidateId") Long candidateId){
        return candidateRepository.findByIdAndEmployeeId(candidateId,employeeId).map(candidate->{
            candidateRepository.delete(candidate);
            return ResponseEntity.ok().build();
        });
    }


}
