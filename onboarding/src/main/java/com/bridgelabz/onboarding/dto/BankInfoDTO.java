package com.bridgelabz.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInfoDTO {
    private Long candidateId;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
}