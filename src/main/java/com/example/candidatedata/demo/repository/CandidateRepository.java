package com.example.candidatedata.demo.repository;

import com.example.candidatedata.demo.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Page<Candidate> findByEmployeeId(Long employeeId, Pageable pageable);
    Optional<Candidate> findByIdAndEmployeeId(Long id, Long employeeId);
}
