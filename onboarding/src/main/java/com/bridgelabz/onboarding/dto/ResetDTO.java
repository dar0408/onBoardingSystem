package com.bridgelabz.onboarding.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetDTO {
    private String email;
    private String otp;
    private String newPassword;
}
