package com.bridgelabz.onboarding.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bridgelabz.onboarding.entity.Role;
import com.bridgelabz.onboarding.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadRoles(RoleRepository roleRepo) {
        return args -> {
            if (roleRepo.count() == 0) {
                Role hr   = new Role();
                hr.setName("ROLE_HR");
                Role admin = new Role();
                admin.setName("ROLE_ADMIN");
                roleRepo.save(hr);
                roleRepo.save(admin);
                System.out.println("Seeded ROLE_HR and ROLE_ADMIN");
            }
        };
    }

}
