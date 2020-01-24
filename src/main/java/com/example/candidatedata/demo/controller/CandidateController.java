package com.example.candidatedata.demo.controller;

import com.example.candidatedata.demo.model.Candidate;
import com.example.candidatedata.demo.repository.CandidateRepository;
import com.example.candidatedata.demo.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CandidateController {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    //get
    @GetMapping("/recruiters/{recruiterId}/candidates")
    public Page<Candidate> getAllCandidatesByRecruiterId(@PathVariable(value="recruiterId") Long recruiterId, Pageable pageable){
        return candidateRepository.findByRecruiterId(recruiterId, pageable);
    }

    //post
    @PostMapping("/recruiters/{recruiterId}/candidates")
    public Optional<Candidate> addCandidate(@PathVariable (value = "recruiterId") Long recruiterId, @Valid @RequestBody Candidate candidate){
        return recruiterRepository.findById(recruiterId).map(post -> {
            candidate.setRecruiter(post);
            return candidateRepository.save(candidate);
        });
    }

    //put
    @PutMapping("/recruiters/{recruiterId}/candidates/{candidateId}")
    public Optional<Candidate> updateRecruiterDetails(@PathVariable (value="recruiterId") Long recruiterId,
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
    public Optional<?> deleteCandidate(@PathVariable(value = "recruiterId") Long recruiterId,
                                                @PathVariable(value = "candidateId") Long candidateId){
        return candidateRepository.findByIdAndRecruiterId(candidateId,recruiterId).map(candidate->{
            candidateRepository.delete(candidate);
            return ResponseEntity.ok().build();
        });
    }


}
