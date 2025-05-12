package com.bridgelabz.onboarding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "educational_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalInfo extends Auditable {
    @Id
    private Long candidateId;

    @NotBlank
    private String highestDegree;

    @NotBlank
    private String university;

    private Double percentage;
}