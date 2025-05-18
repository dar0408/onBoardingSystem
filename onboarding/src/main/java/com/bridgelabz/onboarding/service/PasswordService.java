package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.entity.AppUser;
import com.bridgelabz.onboarding.entity.PasswordResetToken;
import com.bridgelabz.onboarding.repository.AppUserRepository;
import com.bridgelabz.onboarding.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final AppUserRepository userRepo;
    private final PasswordResetTokenRepository tokenRepo;
    private final JavaMailSender mailSender;
    private final PasswordEncoder encoder;

    public void sendOtp(String email) {
        AppUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with that email"));

        String otp = String.format("%06d", new Random().nextInt(1_000_000));
        PasswordResetToken prt = new PasswordResetToken();
        prt.setAppUser(user);
        prt.setOtp(otp);
        prt.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        tokenRepo.save(prt);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject("Your Password Reset OTP");
        mail.setText("Use this OTP to reset your password (valid 15 minutes):\n\n" + otp);
        mailSender.send(mail);
    }

    public void resetPassword(String email, String otp, String newPassword) {
        PasswordResetToken prt = tokenRepo.findByAppUser_EmailAndOtp(email, otp)
                .orElseThrow(() -> new BadCredentialsException("Invalid OTP"));
        if (prt.getExpiresAt().isBefore(LocalDateTime.now())) {
            tokenRepo.delete(prt);
            throw new BadCredentialsException("OTP expired");
        }

        AppUser user = prt.getAppUser();
        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
        tokenRepo.delete(prt);
    }


    @Scheduled(cron = "0 0 * * * *")
    public void purgeExpired() {
        tokenRepo.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
