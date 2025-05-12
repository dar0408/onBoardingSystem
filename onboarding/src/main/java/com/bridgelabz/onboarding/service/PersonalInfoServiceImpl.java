package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.PersonalInfoDTO;
import com.bridgelabz.onboarding.entity.PersonalInfo;
import com.bridgelabz.onboarding.exception.CandidateNotFoundException;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.bridgelabz.onboarding.repository.PersonalInfoRepository;
import com.bridgelabz.onboarding.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final CandidateRepository candidateRepo;
    private final PersonalInfoRepository personalRepo;

    @Override
    @Transactional
    public PersonalInfoDTO updatePersonalInfo(Long candidateId, PersonalInfoDTO dto) {
        try {
            if (!candidateRepo.existsById(candidateId)) throw new CandidateNotFoundException(candidateId);
            dto.setCandidateId(candidateId);
            PersonalInfo entity = MapperUtil.toEntity(dto);
            PersonalInfo saved = personalRepo.save(entity);
            return MapperUtil.toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error updating personal info", e);
        }
    }

    @Override
    public PersonalInfoDTO getPersonalInfo(Long candidateId) {
        try {
            PersonalInfo p = personalRepo.findById(candidateId)
                    .orElseThrow(() -> new CandidateNotFoundException(candidateId));
            return MapperUtil.toDTO(p);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching personal info", e);
        }
    }
}
