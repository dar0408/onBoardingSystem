package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.BankInfoDTO;
import com.bridgelabz.onboarding.entity.BankInfo;
import com.bridgelabz.onboarding.exception.CandidateNotFoundException;
import com.bridgelabz.onboarding.repository.BankInfoRepository;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.bridgelabz.onboarding.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankInfoServiceImpl implements BankInfoService {
    private final CandidateRepository candidateRepo;
    private final BankInfoRepository bankRepo;

    @Override
    @Transactional
    public BankInfoDTO updateBankInfo(Long candidateId, BankInfoDTO dto) {
        if (!candidateRepo.existsById(candidateId)) throw new CandidateNotFoundException(candidateId);
        dto.setCandidateId(candidateId);
        BankInfo entity = MapperUtil.toEntity(dto);
        BankInfo saved = bankRepo.save(entity);
        return MapperUtil.toDTO(saved);
    }

    @Override
    public BankInfoDTO getBankInfo(Long candidateId) {
        BankInfo b = bankRepo.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
        return MapperUtil.toDTO(b);
    }

    @Override
    @Transactional
    public BankInfoDTO createBankInfo(Long candidateId, BankInfoDTO dto) {
        if (!candidateRepo.existsById(candidateId)) throw new CandidateNotFoundException(candidateId);
        dto.setCandidateId(candidateId);
        BankInfo entity = MapperUtil.toEntity(dto);
        BankInfo saved = bankRepo.save(entity);
        return MapperUtil.toDTO(saved);
    }
}
