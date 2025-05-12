package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.EducationalInfoDTO;
import com.bridgelabz.onboarding.entity.EducationalInfo;
import com.bridgelabz.onboarding.exception.CandidateNotFoundException;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.bridgelabz.onboarding.repository.EducationalInfoRepository;
import com.bridgelabz.onboarding.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EducationalInfoServiceImpl implements EducationalInfoService {
    private final CandidateRepository candidateRepo;
    private final EducationalInfoRepository eduRepo;

    @Override
    @Transactional
    public EducationalInfoDTO updateEducationalInfo(Long candidateId, EducationalInfoDTO dto) {
        try {
            if (!candidateRepo.existsById(candidateId)) throw new CandidateNotFoundException(candidateId);
            dto.setCandidateId(candidateId);
            EducationalInfo entity = MapperUtil.toEntity(dto);
            EducationalInfo saved = eduRepo.save(entity);
            return MapperUtil.toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error updating educational info", e);
        }
    }

    @Override
    public EducationalInfoDTO getEducationalInfo(Long candidateId) {
        try {
            EducationalInfo e = eduRepo.findById(candidateId)
                    .orElseThrow(() -> new CandidateNotFoundException(candidateId));
            return MapperUtil.toDTO(e);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching educational info", e);
        }
    }
}
