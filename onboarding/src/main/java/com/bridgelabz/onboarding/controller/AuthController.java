package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.AuthDTO;
import com.bridgelabz.onboarding.dto.JwtResponse;
import com.bridgelabz.onboarding.dto.OtpRequestDTO;
import com.bridgelabz.onboarding.entity.AppUser;
import com.bridgelabz.onboarding.entity.Role;
import com.bridgelabz.onboarding.repository.AppUserRepository;
import com.bridgelabz.onboarding.repository.RoleRepository;
import com.bridgelabz.onboarding.service.JwtUserDetailsService;
import com.bridgelabz.onboarding.service.OtpService;
import com.bridgelabz.onboarding.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthDTO dto) {
        if (userRepo.existsByUsername(dto.getUsername()))
            return ResponseEntity.badRequest().body("Username already exists");
        if (userRepo.existsByEmail(dto.getEmail()))
            return ResponseEntity.badRequest().body("Email already exists");

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role defaultRole = roleRepo.findByName("ROLE_HR")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(Set.of(defaultRole));

        userRepo.save(user);

        otpService.generateOtpAndSend(dto.getEmail());

        return ResponseEntity.ok("OTP sent to email. Please verify.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpRequestDTO dto) {
        if (!otpService.verifyOtp(dto.getEmail(), dto.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }

        Optional<AppUser> userOpt = userRepo.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("User not found");

        AppUser user = userOpt.get();
        user.setVerified(true);
        userRepo.save(user);

        otpService.clearOtp(dto.getEmail());

        return ResponseEntity.ok("User verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO dto) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        Optional<AppUser> userOpt = userRepo.findByUsername(dto.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        AppUser user = userOpt.get();
        if (!user.isVerified()) {
            return ResponseEntity.badRequest().body("Please verify your email before logging in");
        }

        var userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
