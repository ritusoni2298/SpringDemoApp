package com.example.candidatedata.demo.repository;

import com.example.candidatedata.demo.model.Candidate;
import com.example.candidatedata.demo.model.Recruiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Page<Candidate> findByRecruiterId(Long recruiterId, Pageable pageable);
    Optional<Candidate> findByIdAndRecruiterId(Long id, Long recruiterId);
}
