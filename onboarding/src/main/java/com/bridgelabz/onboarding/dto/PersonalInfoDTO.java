package com.bridgelabz.onboarding.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfoDTO {
    private Long candidateId;
    private String address;
    private String fatherName;
    private String motherName;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}