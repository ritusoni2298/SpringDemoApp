package com.example.candidatedata.demo.repository;

import com.example.candidatedata.demo.model.Role;
import com.example.candidatedata.demo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
