package com.bridgelabz.onboarding.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalInfoDTO {
    private Long candidateId;
    private String highestDegree;
    private String university;
    private Double percentage;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}