package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.onboarding.dto.*;
import com.bridgelabz.onboarding.entity.*;
import com.bridgelabz.onboarding.repository.*;
import com.bridgelabz.onboarding.config.JwtUtil;

import java.util.Set;

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
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
        AppUser u = new AppUser();
        u.setUsername(dto.getUsername());
        u.setPassword(encoder.encode(dto.getPassword()));
        var role = roleRepo.findByName("ROLE_HR")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        u.setRoles(Set.of(role));
        userRepo.save(u);
        return ResponseEntity.ok("User registered");
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
