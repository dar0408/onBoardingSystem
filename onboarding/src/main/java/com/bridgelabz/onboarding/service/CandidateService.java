package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.CandidateDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;

import java.util.List;

public interface CandidateService {

    List<CandidateDTO> getAllCandidates();

    List<CandidateDTO> getCandidatesByStatus(String status);

    CandidateDTO getCandidateById(Long id);

    long countCandidatesByStatus(String status);

    ResponseDTO<String> updateStatus(Long id, String status);

    ResponseDTO<String> notifyJobOffer(Long id);

    CandidateDTO createCandidate(CandidateDTO dto);
}
