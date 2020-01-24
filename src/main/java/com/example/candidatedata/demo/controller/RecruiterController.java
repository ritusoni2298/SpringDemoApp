package com.example.candidatedata.demo.controller;

import com.example.candidatedata.demo.model.Recruiter;
import com.example.candidatedata.demo.repository.RecruiterRepository;
import com.example.candidatedata.demo.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;

    //get
    @GetMapping("/recruiters")
    public List<Recruiter> getAllRecruiters(){
        return recruiterService.getAllRecruiters();
    }

    //post
    @PostMapping("/recruiters")
    public Recruiter createRecruiter(@Valid @RequestBody Recruiter recruiter){
        return recruiterService.createRecruiter(recruiter);
    }

    //put
    @PutMapping("/recruiters/{recruiterId}")
    public Optional<Recruiter> updateRecruiter(@PathVariable Long recruiterId,@Valid @RequestBody Recruiter recruiter){
        return recruiterService.updateRecruiter(recruiterId,recruiter);
    }

    //delete
    @DeleteMapping("/recruiters/{recruiterId}")
    public Optional<?> deletePost(@PathVariable Long recruiterId){
        return recruiterService.deletePost(recruiterId);
    }

}
