package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCandidateId(Long candidateId);
}
