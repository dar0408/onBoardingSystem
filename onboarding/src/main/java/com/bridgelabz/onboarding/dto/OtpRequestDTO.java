package com.bridgelabz.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OtpRequestDTO {
    @Email
    private String email;

    @NotBlank
    private String otp;
}
