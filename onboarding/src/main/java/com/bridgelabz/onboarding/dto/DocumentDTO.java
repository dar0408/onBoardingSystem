package com.bridgelabz.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
    private Long id;
    private Long candidateId;
    private String fileName;
    private String fileType;
    private boolean verified;
}