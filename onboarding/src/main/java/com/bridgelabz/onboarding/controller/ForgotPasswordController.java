package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.ResetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.onboarding.service.PasswordService;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/request")
    public ResponseEntity<?> requestOtp(@RequestParam String email) {
        passwordService.sendOtp(email);
        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody ResetDTO dto) {
        passwordService.resetPassword(dto.getEmail(), dto.getOtp(), dto.getNewPassword());
        return ResponseEntity.ok("Password updated");
    }
}
