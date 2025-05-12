package com.bridgelabz.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfoDTO {
    private Long candidateId;
    private String address;
    private String fatherName;
    private String motherName;
}