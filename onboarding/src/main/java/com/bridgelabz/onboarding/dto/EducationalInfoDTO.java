package com.bridgelabz.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalInfoDTO {
    private Long candidateId;
    private String highestDegree;
    private String university;
    private Double percentage;
}