package com.bridgelabz.onboarding.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInfoDTO {
    private Long candidateId;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}