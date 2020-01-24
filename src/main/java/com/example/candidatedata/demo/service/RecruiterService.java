package com.example.candidatedata.demo.service;

import com.example.candidatedata.demo.model.Recruiter;
import com.example.candidatedata.demo.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    public List<Recruiter> getAllRecruiters(){
        return recruiterRepository.findAll();
    }

    public Recruiter createRecruiter(Recruiter recruiter){
        return recruiterRepository.save(recruiter);
    }

    public Optional<Recruiter> updateRecruiter(@PathVariable Long recruiterId, @Valid @RequestBody Recruiter recruiter){
        return recruiterRepository.findById(recruiterId).map(recruiterr-> {
            recruiterr.setFirstName(recruiter.getFirstName());
            recruiterr.setLastName(recruiter.getLastName());
            recruiterr.setEmail(recruiter.getEmail());
            return recruiterRepository.save(recruiterr);
        });
    }

    public Optional<?> deletePost(@PathVariable Long recruiterId){
        return recruiterRepository.findById(recruiterId).map(
                recruiter1-> {
                    recruiterRepository.delete(recruiter1);
                    return ResponseEntity.ok().build();
                }
        );
    }

}
