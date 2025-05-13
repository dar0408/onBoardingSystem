package com.bridgelabz.onboarding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="password_reset_tokens")
public class PasswordResetToken {
    @Id
    @GeneratedValue private Long id;
    private String email;
    private String otp;
    private LocalDateTime expiresAt;
    // getters/setters
}
