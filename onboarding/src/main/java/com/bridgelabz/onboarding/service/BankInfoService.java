package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.BankInfoDTO;

public interface BankInfoService {
    BankInfoDTO updateBankInfo(Long candidateId, BankInfoDTO dto);
    BankInfoDTO getBankInfo(Long candidateId);
    BankInfoDTO createBankInfo(Long candidateId, BankInfoDTO dto); // New method to create bank info
}
