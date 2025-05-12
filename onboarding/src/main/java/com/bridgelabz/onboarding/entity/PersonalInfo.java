package com.bridgelabz.onboarding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "personal_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfo extends Auditable{
    @Id
    private Long candidateId;

    @NotBlank
    private String address;

    @NotBlank
    private String fatherName;

    @NotBlank
    private String motherName;
}
