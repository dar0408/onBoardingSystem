package com.bridgelabz.onboarding.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.onboarding.entity.PasswordResetToken;
import com.bridgelabz.onboarding.repository.AppUserRepository;
import com.bridgelabz.onboarding.repository.PasswordResetTokenRepository;

@Service
public class PasswordService {

    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void sendOtp(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        String otp = String.valueOf(new SecureRandom().nextInt(900000) + 100000); // 6-digit OTP

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(normalizedEmail);
        token.setOtp(otp);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));

        tokenRepo.save(token);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(normalizedEmail);
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP is: " + otp);
        mailSender.send(msg);

        System.out.println("OTP sent to: " + normalizedEmail + " | OTP: " + otp);
    }

    public void resetPassword(String email, String otp, String newPass) {
        String normalizedEmail = email.trim().toLowerCase();
        System.out.println("Attempting password reset for: " + normalizedEmail);

        var token = tokenRepo.findByEmailAndOtp(normalizedEmail, otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        var userOpt = userRepo.findByUsername(normalizedEmail);
        if (userOpt.isEmpty()) {
            System.out.println("No user found for email: " + normalizedEmail);
            throw new RuntimeException("User not found");
        }

        var user = userOpt.get();
        user.setPassword(encoder.encode(newPass));
        userRepo.save(user);
        tokenRepo.delete(token);

        System.out.println("Password reset successfully for: " + normalizedEmail);
    }
}
