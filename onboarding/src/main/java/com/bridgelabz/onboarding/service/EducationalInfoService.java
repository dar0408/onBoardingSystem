package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.EducationalInfoDTO;

public interface EducationalInfoService {
    EducationalInfoDTO updateEducationalInfo(Long candidateId, EducationalInfoDTO dto);
    EducationalInfoDTO getEducationalInfo(Long candidateId);
}
