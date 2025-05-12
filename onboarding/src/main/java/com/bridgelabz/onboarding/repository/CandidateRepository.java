package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByStatus(String status);
    long countByStatus(String status);
}