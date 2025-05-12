package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.PersonalInfoDTO;

public interface PersonalInfoService {
    PersonalInfoDTO updatePersonalInfo(Long candidateId, PersonalInfoDTO dto);
    PersonalInfoDTO getPersonalInfo(Long candidateId);
}
