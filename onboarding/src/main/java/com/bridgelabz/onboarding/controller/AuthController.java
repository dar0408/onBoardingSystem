package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.service.JwtUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.onboarding.dto.*;
import com.bridgelabz.onboarding.entity.*;
import com.bridgelabz.onboarding.repository.*;
import com.bridgelabz.onboarding.config.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AppUserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder encoder;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthDTO dto) {
        if (userRepo.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        if (userRepo.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());               // ← don’t forget this
        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthDTO dto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        var userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtUtil.generateToken((org.springframework.security.core.userdetails.User)userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


}
