package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByAppUser_EmailAndOtp(String email, String otp);
    void deleteByExpiresAtBefore(LocalDateTime now);
}
