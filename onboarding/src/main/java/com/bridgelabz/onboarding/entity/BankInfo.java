package com.bridgelabz.onboarding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bank_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInfo extends Auditable {
    @Id
    private Long candidateId;

    @NotBlank
    private String bankName;

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String ifscCode;
}
